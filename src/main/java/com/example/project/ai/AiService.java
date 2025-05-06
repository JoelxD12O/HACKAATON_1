package com.example.project.ai;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AiService {

    @Value("${openai.api.key}")  // La clave de API de OpenAI desde application.properties
    private String apiKey;

    private String endpoint = "https://models.github.ai/inference";  // Endpoint de GitHub Models
    private String model = "openai/o4-mini";  // Nombre del modelo de OpenAI

    public String queryChatModel(String query) {
        // Crear cliente para interactuar con la API de OpenAI
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        List<ChatRequestMessage> chatMessages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage(query)
        );

        // Configuración de las opciones de completado
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(model);

        // Realizar la consulta al modelo y obtener la respuesta
        ChatCompletions completions = client.complete(chatCompletionsOptions);

        // Verificar si la lista de respuestas no está vacía
        if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
            return completions.getChoices().get(0).getMessage().getContent();
        } else {
            return "No response from the model.";
        }
    }
}
