package com.example.project.ai.models;
import com.example.project.ai.models.AIRequestDTO;


public interface IAiModelService {
    String processPrompt(AIRequestDTO request);

    String processPromt(AIRequestDTO request);

    String getModelName(); // para identificar el modelo ("DeepSpeak", "OpenAI", etc.)
}
