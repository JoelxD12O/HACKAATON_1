package com.example.project.ai.models;
import com.example.project.ai.models.dto.DeepSeekRequestDTO;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class DeepSpeakService implements IAiModelService{
    @Value("${deepseek.api.key}")
    private String apiKey;

    @Value("${deepseek.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public DeepSpeakService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String generateResponse(String prompt) {
        DeepSeekRequestDTO request = new DeepSeekRequestDTO();
        request.setModel("deepseek-chat");
        request.setPrompt(prompt);
        request.setMaxTokens(150);
        request.setTemperature(0.7);

        return sendRequestToDeepSeek(request);
    }

    public String sendRequestToDeepSeek(DeepSeekRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<DeepSeekRequestDTO> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Error al conectar con DeepSeek: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con el servicio DeepSeek", e);
        }
    }

    @Override
    public String getModelName() {
        return "DeepSeek";
    }

    @Override
    public boolean supports(String modelType) {
        return "deepseek".equalsIgnoreCase(modelType);
    }
}
