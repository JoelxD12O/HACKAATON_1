package com.example.project.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;  // Inyectar el servicio que maneja la interacción con los modelos de IA

    // Endpoint para realizar una consulta al modelo de OpenAI
    @PostMapping("/openai/chat")
    public String queryOpenAiChat(@RequestBody String query) {
        return aiService.queryChatModel(query);
    }

}
