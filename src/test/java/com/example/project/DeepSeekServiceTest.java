package com.example.project;
import com.example.project.ai.models.DeepSpeakService;
import com.example.project.ai.models.dto.DeepSeekRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class DeepSeekServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DeepSpeakService deepSeekService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateResponse() {
        // Configurar el mock
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Respuesta simulada", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class)))
                .thenReturn(mockResponse);

        // Ejecutar el metodo
        String result = deepSeekService.generateResponse("Test prompt");

        // Verificar resultados
        assertNotNull(result);
        assertEquals("Respuesta simulada", result);

        // Verificar interacciones
        verify(restTemplate, times(1)).exchange(anyString(), any(), any(), eq(String.class));
    }

    @Test
    void testSupports() {
        assertTrue(deepSeekService.supports("deepseek"));
        assertTrue(deepSeekService.supports("DEEPSEEK"));
        assertFalse(deepSeekService.supports("openai"));
    }

    @Test
    void testGetModelName() {
        assertEquals("DeepSeek", deepSeekService.getModelName());
    }
}

