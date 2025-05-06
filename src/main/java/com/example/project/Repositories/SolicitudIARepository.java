package com.example.project.Repositories;

import com.example.project.Entidades.SolicitudIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudIARepository extends JpaRepository<SolicitudIA, Long> {
    List<SolicitudIA> findByUsuarioId(Long usuarioId);
}
