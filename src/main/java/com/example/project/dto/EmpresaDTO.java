package com.example.project.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmpresaDTO {

    private Long id;
    private String nombre;
    private String ruc;
    private LocalDate fechaAfiliacion;
    private boolean activa;
}