package com.example.project.controllers;

import com.example.project.dto.EmpresaConsumoDTO;
import com.example.project.dto.EmpresaRequestDTO;
import com.example.project.dto.EmpresaDTO;
import com.example.project.dto.UsuarioRequestDTO;
import com.example.project.enums.Rol;
import com.example.project.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class CompanyAdminController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDTO createCompany(
            @Valid @RequestBody EmpresaRequestDTO empresaRequest,
            @Valid @RequestBody UsuarioRequestDTO adminRequest) {
        adminRequest.setRol(Rol.ROLE_COMPANY_ADMIN);
        return companyService.crearEmpresa(empresaRequest, adminRequest);
    }
/*
    @GetMapping("/{id}/consumption")
    public ResponseEntity<EmpresaConsumoDTO> getConsumption(@PathVariable Long id) {
        EmpresaConsumoDTO consumo = companyService.obtenerConsumoEmpresa(id);
        return ResponseEntity.ok(consumo);
    }*/
}
