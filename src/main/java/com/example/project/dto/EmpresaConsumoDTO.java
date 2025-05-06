package com.example.project.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EmpresaConsumoDTO {

    private Long empresaId;
    private String nombreEmpresa;

    private Map<String, Long> consumoPorModelo;
}