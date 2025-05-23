package com.example.project.Entidades;

import com.example.project.enums.TipoLimite;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimiteUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modeloIA;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLimite tipoLimite;

    private Long valorLimite;

    private String ventanaTiempo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
