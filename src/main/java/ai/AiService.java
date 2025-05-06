package ai;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AiService {

    @Value("${openai.api.key}")  // La clave de API para OpenAI desde application.properties
    private String apiKey;

    private String endpoint = "https://models.github.ai/inference";  // Endpoint de GitHub Models
    private String model = "openai/o4-mini";  // Nombre del modelo de OpenAI

    public String queryChatModel(String query) {
        // Configuración del cliente de Azure AI para interactuar con el modelo de OpenAI
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        List<ChatRequestMessage> chatMessages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage(query)
        );

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(model);

        // Realizar la consulta al modelo y obtener la respuesta
        ChatCompletions completions = client.complete(chatCompletionsOptions);
        return completions.getChoice().getMessage().getContent();
    }
}
