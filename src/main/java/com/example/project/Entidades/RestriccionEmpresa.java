package com.example.project.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestriccionEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modeloIA;

    private String tipoLimite;

    private Long valorLimite;

    private String ventanaTiempo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}