package com.example.project.service;

import com.example.project.Entidades.Empresa;
import com.example.project.Entidades.Usuario;
import com.example.project.Repositories.EmpresaRepository;
import com.example.project.Repositories.UsuarioRepository;
import com.example.project.dto.EmpresaDTO;
import com.example.project.dto.EmpresaRequestDTO;
import com.example.project.dto.UsuarioRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

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
}