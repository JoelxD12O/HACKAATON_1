package com.example.project.dto;

import com.example.project.enums.TipoLimite;
import lombok.Data;

@Data
public class RestriccionEmpresaRequestDTO {

    private String modeloIA;

    private TipoLimite tipoLimite;

    private Long valorLimite;

    private String ventanaTiempo;

    private Long empresaId;
}
