package com.example.project.Entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SolicitudIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String consulta;  // Consulta enviada al modelo de IA

    private String respuesta; // Respuesta del modelo de IA

    private int tokensConsumidos; // Tokens consumidos en la consulta

    private Date fecha; // Fecha de la solicitud

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;  // Relación con el Usuario

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

