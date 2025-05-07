package com.example.project.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class EmpresaUpdateDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Size(min = 11, max = 11, message = "El RUC debe tener 11 dígitos")
    private String ruc;

    private boolean activa;
}