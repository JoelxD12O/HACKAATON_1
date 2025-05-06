package controllers;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @PostMapping("/chat")
    public ResponseEntity<AIResponse> chat(@RequestBody AIRequestDto request, Principal principal) {
        // Procesar consulta con límites
    }

    @GetMapping("/history")
    public ResponseEntity<List<AIRequest>> getHistory(Principal principal) {
        // Historial del usuario
    }
}
