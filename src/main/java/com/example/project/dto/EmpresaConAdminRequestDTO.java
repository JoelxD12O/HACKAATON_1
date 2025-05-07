package com.example.project.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class EmpresaConAdminRequestDTO {
    @Valid
    private EmpresaRequestDTO empresaRequest;
    @Valid
    private UsuarioRequestDTO adminRequest;
}
