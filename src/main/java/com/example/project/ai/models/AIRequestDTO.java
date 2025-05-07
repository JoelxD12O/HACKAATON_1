package com.example.project.ai.models;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AIRequestDTO {

    @NotBlank(message = "El prompt no puede estar vacío")
    private String prompt;

    public AIRequestDTO() {}

    public AIRequestDTO(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}