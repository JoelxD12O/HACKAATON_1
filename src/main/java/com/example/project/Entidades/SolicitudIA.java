package com.example.project.Entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class SolicitudIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String consulta;
    private String respuesta;
    private int tokensConsumidos;
    private LocalDateTime fecha;
    private String modelo; // Nuevo
    private boolean exitosa; // Nuevo

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa; // Nuevo
}