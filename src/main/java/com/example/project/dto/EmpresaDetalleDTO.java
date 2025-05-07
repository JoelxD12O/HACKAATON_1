package com.example.project.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EmpresaDetalleDTO {
    private Long id;
    private String nombre;
    private String ruc;
    private boolean activa;
    private LocalDate fechaAfiliacion;
    private List<UsuarioInfoDTO> administradores;


}