package com.example.project.controllers;

import org.springframework.web.bind.annotation.*;

public class CompanyAdminController {
    @RestController
    @RequestMapping("/api/admin/companies")
    public class CompanyAdminController {

        @PostMapping
        public ResponseEntity<CompanyResponse> createCompany(@RequestBody CreateCompanyRequest request) {
            // Lógica para crear empresa + admin
        }

        @GetMapping("/{id}/consumption")
        public ResponseEntity<CompanyConsumptionResponse> getConsumption(@PathVariable Long id) {
            // Reporte de consumo
        }
    }

}
