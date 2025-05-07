package com.example.project.service;

import com.example.project.Entidades.LimiteUsuario;
import com.example.project.Entidades.Usuario;
import com.example.project.Repositories.LimiteUsuarioRepository;
import com.example.project.dto.AsignarLimiteRequestDTO;
import com.example.project.dto.LimiteUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimiteUsuarioRepository limiteUsuarioRepository;

    public LimiteUsuarioDTO asignarLimiteAUsuario(Long userId, AsignarLimiteRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setId(userId);

        LimiteUsuario limite = LimiteUsuario.builder()
                .modeloIA(request.getModeloIA())
                .tipoLimite(request.getTipoLimite())
                .valorLimite(request.getValorLimite())
                .ventanaTiempo(request.getVentanaTiempo())
                .usuario(usuario)
                .build();

        LimiteUsuario guardado = limiteUsuarioRepository.save(limite);

        return LimiteUsuarioDTO.builder()
                .id(guardado.getId())
                .modeloIA(guardado.getModeloIA())
                .tipoLimite(guardado.getTipoLimite())
                .valorLimite(guardado.getValorLimite())
                .ventanaTiempo(guardado.getVentanaTiempo())
                .usuarioId(userId)
                .build();
    }

    public void checkLimits(Usuario usuario, String modeloIA) {
        List<LimiteUsuario> limites = limiteUsuarioRepository.findAll();

        boolean excedido = false;

        if (excedido) {
            throw new RuntimeException("Límite de uso excedido para el modelo: " + modeloIA);
        }
    }
}