package com.example.project.ai.models;

public interface IAiModelService {
    String generateResponse(String prompt);
    String getModelName();
    boolean supports(String modelType);
}