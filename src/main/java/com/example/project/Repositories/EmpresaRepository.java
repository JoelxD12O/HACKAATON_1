package com.example.project.Repositories;

import com.example.project.Entidades.Empresa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @EntityGraph(attributePaths = {"usuarios"})
    Optional<Empresa> findById(Long id);
}