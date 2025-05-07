package com.example.project.Repositories;

import com.example.project.Entidades.SolicitudIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudIARepository extends JpaRepository<SolicitudIA, Long> {
    List<SolicitudIA> findByUsuarioId(Long usuarioId);
    // Consulta optimizada para el reporte
    @Query("""
        SELECT s.modelo AS modelo, 
               SUM(s.tokensConsumidos) AS gastado,
               COUNT(s.id) AS totalSolicitudes
        FROM SolicitudIA s 
        WHERE s.empresa.id = :empresaId
        GROUP BY s.modelo
        """)
    List<ConsumoPorModeloProjection> findConsumoPorModelo(Long empresaId);

    // Total de tokens consumidos por la empresa
    @Query("SELECT COALESCE(SUM(s.tokensConsumidos), 0) FROM SolicitudIA s WHERE s.empresa.id = :empresaId")
    int sumTokensConsumidosByEmpresa(Long empresaId);

    // Interface para la proyección
    interface ConsumoPorModeloProjection {
        String getModelo();
        Integer getGastado();
        Long getTotalSolicitudes();
    }
}
