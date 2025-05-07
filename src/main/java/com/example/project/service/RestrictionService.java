package com.example.project.service;

import com.example.project.Entidades.Empresa;
import com.example.project.Entidades.RestriccionEmpresa;
import com.example.project.Repositories.EmpresaRepository;
import com.example.project.Repositories.RestriccionEmpresaRepository;
import com.example.project.dto.RestriccionEmpresaDTO;
import com.example.project.dto.RestriccionEmpresaRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestrictionService {

    private final RestriccionEmpresaRepository restriccionRepo;
    private final EmpresaRepository empresaRepo;

    public RestriccionEmpresaDTO crearRestriccion(RestriccionEmpresaRequestDTO request) {
        Empresa empresa = empresaRepo.findById(request.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        RestriccionEmpresa r = RestriccionEmpresa.builder()
                .empresa(empresa)
                .modeloIA(request.getModeloIA())
                .tipoLimite(request.getTipoLimite())
                .valorLimite(request.getValorLimite())
                .ventanaTiempo(request.getVentanaTiempo())
                .build();

        RestriccionEmpresa saved = restriccionRepo.save(r);
        return toDTO(saved);
    }

    public List<RestriccionEmpresaDTO> obtenerRestriccionesPorEmpresa(Long empresaId) {
        return restriccionRepo.findAll().stream()
                .filter(r -> r.getEmpresa().getId().equals(empresaId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RestriccionEmpresaDTO actualizarRestriccion(Long id, RestriccionEmpresaRequestDTO request) {
        RestriccionEmpresa r = restriccionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restricción no encontrada"));

        r.setModeloIA(request.getModeloIA());
        r.setTipoLimite(request.getTipoLimite());
        r.setValorLimite(request.getValorLimite());
        r.setVentanaTiempo(request.getVentanaTiempo());

        RestriccionEmpresa actualizado = restriccionRepo.save(r);
        return toDTO(actualizado);
    }

    public void eliminarRestriccion(Long id) {
        restriccionRepo.deleteById(id);
    }

    private RestriccionEmpresaDTO toDTO(RestriccionEmpresa r) {
        return RestriccionEmpresaDTO.builder()
                .id(r.getId())
                .modeloIA(r.getModeloIA())
                .tipoLimite(r.getTipoLimite())
                .valorLimite(r.getValorLimite())
                .ventanaTiempo(r.getVentanaTiempo())
                .empresaId(r.getEmpresa().getId())
                .build();
    }
}