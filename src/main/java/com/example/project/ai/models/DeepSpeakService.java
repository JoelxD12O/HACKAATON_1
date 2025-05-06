package com.example.project.ai.models;
import org.springframework.stereotype.Service;

@Service
public abstract class DeepSpeakService implements IAiModelService{

    @Override
    public String processPromt(AIRequestDTO request){
        return "Respuesta simulada de DeepSeek para: " + request.getPrompt();
    }


    @Override
    public String getModelName(){
        return "DeepSeek";
    }
}
