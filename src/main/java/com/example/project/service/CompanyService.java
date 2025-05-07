package com.example.project.service;

import com.example.project.dto.EmpresaDTO;
import com.example.project.dto.EmpresaRequestDTO;
import com.example.project.dto.UsuarioRequestDTO;

public interface CompanyService {
    EmpresaDTO crearEmpresa(EmpresaRequestDTO empresaRequest, UsuarioRequestDTO adminRequest);
}