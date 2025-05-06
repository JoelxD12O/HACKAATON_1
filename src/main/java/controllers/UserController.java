package controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        // Crear usuario (solo COMPANY_ADMIN)
    }

    @PostMapping("/{userId}/limits")
    public ResponseEntity<UserLimit> assignLimit(@PathVariable UUID userId, @RequestBody UserLimitRequest request) {
        // Asignar límites
    }
}

// com.sparky.ai.controller.ai