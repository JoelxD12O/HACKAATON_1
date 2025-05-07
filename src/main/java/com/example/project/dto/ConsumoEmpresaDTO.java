package com.example.project.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ConsumoEmpresaDTO {
    private int totalTokensAsignados;  // Suma de todos los límites de la empresa
    private int totalTokensGastados;
    private int tokensDisponibles;
    private List<ConsumoModeloDTO> consumoPorModelo;

    @Data
    @Builder
    public static class ConsumoModeloDTO {
        private String modelo;
        private int tokensAsignados; // Límite configurado para este modelo
        private int tokensGastados;
        private long totalSolicitudes;
    }
}