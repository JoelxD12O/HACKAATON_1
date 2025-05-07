package com.example.project.service;

import com.example.project.dto.*;

import java.util.List;

public interface CompanyService {
    EmpresaDTO crearEmpresa(EmpresaRequestDTO empresaRequest, UsuarioRequestDTO adminRequest);
    List<EmpresaDTO> listarTodasLasEmpresas();
    EmpresaDetalleDTO obtenerEmpresaPorId(Long id);
    EmpresaDetalleDTO actualizarEmpresa(Long id, EmpresaUpdateDTO dto);
    void cambiarEstadoEmpresa(Long id, boolean estado);


}