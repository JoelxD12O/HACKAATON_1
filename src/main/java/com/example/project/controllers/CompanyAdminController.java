package com.example.project.controllers;

import com.example.project.dto.*;
import com.example.project.enums.Rol;
import com.example.project.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class CompanyAdminController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDTO createCompany(
            @Valid @RequestBody EmpresaConAdminRequestDTO request) {
        request.getAdminRequest().setRol(Rol.ROLE_COMPANY_ADMIN);
        return companyService.crearEmpresa(request.getEmpresaRequest(), request.getAdminRequest());
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarTodasLasEmpresas() {
        List<EmpresaDTO> empresas = companyService.listarTodasLasEmpresas();
        return ResponseEntity.ok(empresas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDetalleDTO> obtenerEmpresaPorId(@PathVariable Long id) {
        // El servicio ya maneja la lógica de orElseThrow
        EmpresaDetalleDTO empresa = companyService.obtenerEmpresaPorId(id);
        return ResponseEntity.ok(empresa);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDetalleDTO> actualizarEmpresa(
            @PathVariable Long id,
            @Valid @RequestBody EmpresaUpdateDTO dto) {
        EmpresaDetalleDTO empresaActualizada = companyService.actualizarEmpresa(id, dto);
        return ResponseEntity.ok(empresaActualizada);
    }
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cambiarEstadoEmpresa(
            @PathVariable Long id,
            @RequestParam(name = "active") boolean activa // Nombre explícito del parámetro
    ) {
        companyService.cambiarEstadoEmpresa(id, activa);
    }
    @GetMapping("/{id}/consumption")
    //@PreAuthorize("hasRole('ROLE_SPARKY_ADMIN')") // Solo para superadmins
    public ResponseEntity<ConsumoEmpresaDTO> obtenerConsumoEmpresa(
            @PathVariable Long id) {
        ConsumoEmpresaDTO consumo = companyService.obtenerConsumoEmpresa(id);
        return ResponseEntity.ok(consumo);
    }
}