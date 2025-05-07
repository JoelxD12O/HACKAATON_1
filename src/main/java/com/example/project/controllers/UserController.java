package com.example.project.controllers;

import com.example.project.dto.UsuarioRequestDTO;
import com.example.project.dto.UsuarioDTO;
import com.example.project.dto.AsignarLimiteRequestDTO;
import com.example.project.dto.LimiteUsuarioDTO;
import com.example.project.service.UserService;
import com.example.project.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LimitService limitService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioRequestDTO request) {
        UsuarioDTO usuario = userService.crearUsuario(request);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/{userId}/limits")
    public ResponseEntity<LimiteUsuarioDTO> assignLimit(
            @PathVariable Long userId,
            @RequestBody AsignarLimiteRequestDTO request) {

        LimiteUsuarioDTO limite = limitService.asignarLimiteAUsuario(userId, request);
        return ResponseEntity.ok(limite);
    }
}