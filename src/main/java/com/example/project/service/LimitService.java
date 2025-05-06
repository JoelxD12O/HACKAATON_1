package com.example.project.service;

import com.azure.ai.inference.models.ModelType;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class LimitService {
    // Validar límites antes de cada solicitud
    public void checkLimits(User user, ModelType model) {
        // Lógica de rate limiting
    }
}