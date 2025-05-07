package com.example.project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioInfoDTO {
    private Long id;
    private String nombreCompleto;
    private String email;
}