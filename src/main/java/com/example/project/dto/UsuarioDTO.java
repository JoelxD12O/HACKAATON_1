package com.example.project.dto;

import com.example.project.enums.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String email;
    private Rol rol;
    private Long empresaId;
}
