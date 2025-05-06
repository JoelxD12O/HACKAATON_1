package com.example.project.controllers;

import com.example.project.dto.EmpresaConsumoDTO;
import com.example.project.dto.EmpresaRequestDTO;
import com.example.project.dto.EmpresaDTO;
import com.example.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class CompanyAdminController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<EmpresaDTO> createCompany(@RequestBody EmpresaRequestDTO request) {
        EmpresaDTO empresa = companyService.crearEmpresa(request);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<EmpresaConsumoDTO> getConsumption(@PathVariable Long id) {
        EmpresaConsumoDTO consumo = companyService.obtenerConsumoEmpresa(id);
        return ResponseEntity.ok(consumo);
    }
}
