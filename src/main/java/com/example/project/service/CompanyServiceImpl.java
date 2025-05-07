package com.example.project.service;

import com.example.project.Repositories.SolicitudIARepository;
import com.example.project.Repositories.RestriccionEmpresaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.project.Entidades.Empresa;
import com.example.project.Entidades.RestriccionEmpresa;
import com.example.project.Entidades.Usuario;
import com.example.project.Repositories.EmpresaRepository;
import com.example.project.Repositories.SolicitudIARepository;
import com.example.project.Repositories.UsuarioRepository;
import com.example.project.dto.*;
import com.example.project.enums.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.project.Exceptions.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final SolicitudIARepository solicitudIARepository; // Inyección del repositorio
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestriccionEmpresaRepository restriccionEmpresaRepository;

    @Override
    @Transactional
    public EmpresaDTO crearEmpresa(EmpresaRequestDTO empresaRequest, UsuarioRequestDTO adminRequest) {
        // 1. Crear y guardar empresa
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaRequest.getNombre());
        empresa.setRuc(empresaRequest.getRuc());
        empresa.setActiva(empresaRequest.isActiva());

        Empresa empresaGuardada = empresaRepository.save(empresa);

        // 2. Crear y guardar administrador
        Usuario administrador = new Usuario();
        administrador.setNombreCompleto(adminRequest.getNombreCompleto());
        administrador.setEmail(adminRequest.getEmail());
        administrador.setPassword(adminRequest.getPassword()); // Sin encriptar
        administrador.setRol(adminRequest.getRol());
        administrador.setEmpresa(empresaGuardada);

        usuarioRepository.save(administrador);

        // 3. Retornar DTO
        EmpresaDTO response = new EmpresaDTO();
        response.setId(empresaGuardada.getId());
        response.setNombre(empresaGuardada.getNombre());
        response.setRuc(empresaGuardada.getRuc());
        response.setFechaAfiliacion(LocalDate.now());
        response.setActiva(empresaGuardada.isActiva());

        return response;
    }

    @Override
    public List<EmpresaDTO> listarTodasLasEmpresas() {
        return empresaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        return EmpresaDTO.builder()
                .id(empresa.getId())
                .nombre(empresa.getNombre())
                .ruc(empresa.getRuc())
                .fechaAfiliacion(empresa.getFechaAfiliacion())
                .activa(empresa.isActiva())
                .build();
    }

    @Override
    public EmpresaDetalleDTO obtenerEmpresaPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + id));

        return EmpresaDetalleDTO.builder()
                .id(empresa.getId())
                .nombre(empresa.getNombre())
                .ruc(empresa.getRuc())
                .activa(empresa.isActiva())
                .fechaAfiliacion(empresa.getFechaAfiliacion())
                .build();
    }

    private List<UsuarioInfoDTO> convertirUsuariosADTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .filter(u -> u.getRol() == Rol.ROLE_COMPANY_ADMIN)
                .map(u -> UsuarioInfoDTO.builder()
                        .id(u.getId())
                        .nombreCompleto(u.getNombreCompleto())
                        .email(u.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public EmpresaDetalleDTO actualizarEmpresa(Long id, EmpresaUpdateDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + id));

        // 2. Actualizar campos
        empresa.setNombre(dto.getNombre());
        empresa.setRuc(dto.getRuc());
        empresa.setActiva(dto.isActiva());

        // 3. Guardar cambios
        Empresa empresaActualizada = empresaRepository.save(empresa);

        // 4. Retornar DTO con detalles
        return EmpresaDetalleDTO.builder()
                .id(empresaActualizada.getId())
                .nombre(empresaActualizada.getNombre())
                .ruc(empresaActualizada.getRuc())
                .activa(empresaActualizada.isActiva())
                .fechaAfiliacion(empresaActualizada.getFechaAfiliacion())
                .administradores(convertirUsuariosADTO(empresaActualizada.getUsuarios()))
                .build();
    }
    @Override
    @Transactional
    public void cambiarEstadoEmpresa(Long id, boolean estado) {
        // 1. Buscar empresa existente
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + id));

        // 2. Actualizar solo el estado
        empresa.setActiva(estado);
        empresaRepository.save(empresa);
    }
    @Transactional(readOnly = true)
    public ConsumoEmpresaDTO obtenerConsumoEmpresa(Long empresaId) {
        // 1. Obtener todas las restricciones (límites por modelo) de la empresa
        List<RestriccionEmpresa> restricciones = restriccionEmpresaRepository.findByEmpresaId(empresaId);

        // 2. Obtener datos de consumo desde SolicitudIA
        // CORRECTO (usando la instancia inyectada):
// CORRECTO (usando la instancia inyectada):
        List<SolicitudIARepository.ConsumoPorModeloProjection> consumoModelos = solicitudIARepository.findConsumoPorModelo(empresaId); // ✅
// ✅ CORRECTO: Llamada desde una instancia
        int totalGastado = solicitudIARepository.sumTokensConsumidosByEmpresa(empresaId);        // 3. Calcular total asignado (suma de todos los límites)

        int totalAsignado = restricciones.stream()
                .mapToInt(RestriccionEmpresa::getLimiteTokens)
                .sum();

        // 4. Mapear a DTO
        List<ConsumoEmpresaDTO.ConsumoModeloDTO> consumoModeloDTOs = restricciones.stream()
                .map(restriccion -> {
                    String modelo = restriccion.getModelo();

                    // Buscar consumo para este modelo
                    int gastado = consumoModelos.stream()
                            .filter(c -> c.getModelo().equals(modelo))
                            .findFirst()
                            .map(SolicitudIARepository.ConsumoPorModeloProjection::getGastado)
                            .orElse(0);

                    long totalSolicitudes = consumoModelos.stream()
                            .filter(c -> c.getModelo().equals(modelo))
                            .findFirst()
                            .map(SolicitudIARepository.ConsumoPorModeloProjection::getTotalSolicitudes)
                            .orElse(0L);

                    return ConsumoEmpresaDTO.ConsumoModeloDTO.builder()
                            .modelo(modelo)
                            .tokensAsignados(restriccion.getLimiteTokens())
                            .tokensGastados(gastado)
                            .totalSolicitudes(totalSolicitudes)
                            .build();
                })
                .toList();

        return ConsumoEmpresaDTO.builder()
                .totalTokensAsignados(totalAsignado)
                .totalTokensGastados(totalGastado)
                .tokensDisponibles(totalAsignado - totalGastado)
                .consumoPorModelo(consumoModeloDTOs)
                .build();
    }
}