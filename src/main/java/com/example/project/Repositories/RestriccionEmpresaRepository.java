package com.example.project.Repositories;

import com.example.project.Entidades.RestriccionEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestriccionEmpresaRepository extends JpaRepository<RestriccionEmpresa, Long> {
    List<RestriccionEmpresa> findByEmpresaId(Long empresaId);

}