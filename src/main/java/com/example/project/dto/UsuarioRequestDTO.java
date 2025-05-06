package com.example.project.dto;

import com.example.project.enums.Rol;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nombreCompleto;
    private String email;
    private String password;
    private Rol rol;
    private Long empresaId;
}