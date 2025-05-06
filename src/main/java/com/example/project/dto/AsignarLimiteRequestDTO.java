package com.example.project.dto;

import com.example.project.enums.TipoLimite;
import lombok.Data;

@Data
public class AsignarLimiteRequestDTO {

    private String modeloIA;
    private TipoLimite tipoLimite;
    private Long valorLimite;
    private String ventanaTiempo;
}