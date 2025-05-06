package com.example.project.ai.models.dto;

import java.util.Date;

public class SolicitudIADTO {

    private String consulta;
    private String respuesta;
    private int tokensConsumidos;
    private Date fecha;

    // Getters and Setters
    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getTokensConsumidos() {
        return tokensConsumidos;
    }

    public void setTokensConsumidos(int tokensConsumidos) {
        this.tokensConsumidos = tokensConsumidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
