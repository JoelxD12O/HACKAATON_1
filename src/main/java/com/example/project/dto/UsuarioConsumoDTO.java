package com.example.project.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class UsuarioConsumoDTO {

    private Long usuarioId;
    private String nombreUsuario;
    private Map<String, Long> consumoPorModelo;
}
