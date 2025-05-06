package com.example.project.dto.empresaDTO;

import com.example.project.enums.TipoLimite;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestriccionEmpresaDTO {

    private Long id;

    private String modeloIA;

    private TipoLimite tipoLimite;

    private Long valorLimite;

    private String ventanaTiempo;

    private Long empresaId;
}
