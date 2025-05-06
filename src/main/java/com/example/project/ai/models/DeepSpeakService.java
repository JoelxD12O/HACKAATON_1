package com.example.project.ai.models;
import org.springframework.stereotype.Service;
import ai.dto.AIRequestDTO;

@Service
public class DeepSpeakService implements IAiModelService{

    @Override
    public String processPromt(AIRequestDTO request){
        return "Respuesta simulada de DeepSeek para: " + request.getPrompt();
    }


    @Override
    private String getModelName(){
        return "DeepSeek";
    }
}
