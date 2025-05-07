package com.example.project.Entidades;

import com.example.project.enums.TipoLimite;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestriccionEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;//este es el modelo IA
    private int limiteTokens;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLimite tipoLimite;

    private Long valorLimite;

    private String ventanaTiempo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}