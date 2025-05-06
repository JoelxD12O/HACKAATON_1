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

import com.example.project.ai.models.IAiModelService;
import com.example.project.ai.models.AIRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${openai.api.key}")  // La clave de API de OpenAI desde application.properties
    private String apiKey;

    private String endpoint = "https://models.github.ai/inference";  // Endpoint de GitHub Models
    private String model = "openai/o4-mini";  // Nombre del modelo de OpenAI

    private final Map<String, IAiModelService> modelServices = new HashMap<>();

    @Autowired
    public AiService(List<IAiModelService> services) {
        for (IAiModelService service : services) {
            modelServices.put(service.getModelName(), service);
        }
    }

    public String enviarPrompt(String modelName, AIRequestDTO request) {
        IAiModelService service = modelServices.get(modelName);
        if (service == null) throw new IllegalArgumentException("Modelo no soportado");
        return service.processPrompt(request);
    }




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
