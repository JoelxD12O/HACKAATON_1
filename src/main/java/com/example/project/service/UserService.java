package com.example.project.service;

import com.example.project.Entidades.Empresa;
import com.example.project.Entidades.Usuario;
import com.example.project.Repositories.EmpresaRepository;
import com.example.project.Repositories.UsuarioRepository;
import com.example.project.dto.UsuarioDTO;
import com.example.project.dto.UsuarioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    public UsuarioDTO crearUsuario(UsuarioRequestDTO request) {
        Empresa empresa = empresaRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Usuario usuario = Usuario.builder()
                .nombreCompleto(request.getNombreCompleto())
                .email(request.getEmail())
                .password(request.getPassword())
                .rol(request.getRol())
                .empresa(empresa)
                .build();

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioDTO.builder()
                .id(guardado.getId())
                .nombreCompleto(guardado.getNombreCompleto())
                .email(guardado.getEmail())
                .rol(guardado.getRol())
                .empresaId(empresa.getId())
                .build();
    }
}