package com.example.project.controllers;

import com.example.project.dto.RestriccionEmpresaDTO;
import com.example.project.dto.RestriccionEmpresaRequestDTO;
import com.example.project.service.RestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
@RequiredArgsConstructor
public class RestrictionController {

    private final RestrictionService restrictionService;

    @PostMapping
    public ResponseEntity<RestriccionEmpresaDTO> crear(@RequestBody RestriccionEmpresaRequestDTO request) {
        return ResponseEntity.ok(restrictionService.crearRestriccion(request));
    }

    @GetMapping
    public ResponseEntity<List<RestriccionEmpresaDTO>> listar(@RequestParam Long empresaId) {
        return ResponseEntity.ok(restrictionService.obtenerRestriccionesPorEmpresa(empresaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestriccionEmpresaDTO> actualizar(@PathVariable Long id, @RequestBody RestriccionEmpresaRequestDTO request) {
        return ResponseEntity.ok(restrictionService.actualizarRestriccion(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        restrictionService.eliminarRestriccion(id);
        return ResponseEntity.noContent().build();
    }
}