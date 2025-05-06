package com.example.project.ai.models;

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
public class OpenAiService {

    @Value("${openai.api.key}")  // Leer la clave de API de OpenAI desde application.properties
    private String apiKey;

    private String endpoint = "https://models.github.ai/inference";  // Endpoint de GitHub Models (esto puede cambiar según tu configuración)
    private String model = "openai/o4-mini";  // Nombre del modelo de OpenAI

    /**
     * Realiza una consulta al modelo de OpenAI
     * @param query La consulta que se enviará al modelo de OpenAI
     * @return La respuesta generada por el modelo
     */
    public String queryChatModel(String query) {
        // Crear cliente para interactuar con la API de OpenAI
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))  // Autenticación con el token de API
                .endpoint(endpoint)
                .buildClient();

        // Crear los mensajes que se enviarán al modelo
        List<ChatRequestMessage> chatMessages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),  // Mensaje de sistema (contexto)
                new ChatRequestUserMessage(query)  // Mensaje del usuario (la consulta)
        );

        // Configurar las opciones para el modelo de completado
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(model);  // Establecer el modelo de OpenAI

        // Realizar la consulta al modelo de IA
        ChatCompletions completions = client.complete(chatCompletionsOptions);

        // Verificar si la respuesta contiene opciones y devolver la primera opción
        if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
            return completions.getChoices().get(0).getMessage().getContent();  // Devuelve el contenido de la respuesta
        } else {
            return "No response from the model.";  // Mensaje predeterminado si no hay respuesta
        }
    }
}
