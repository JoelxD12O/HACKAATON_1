package com.example.project.service;

import com.example.project.Entidades.SolicitudIA;
import com.example.project.Entidades.Usuario;
import com.example.project.Repositories.SolicitudIARepository;
import com.example.project.ai.models.dto.SolicitudIADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudIAService {

    @Autowired
    private SolicitudIARepository solicitudIARepository;

    // Crear una nueva solicitud de IA
    public SolicitudIA crearSolicitud(SolicitudIADTO solicitudIADTO, Long usuarioId) {
        SolicitudIA solicitudIA = new SolicitudIA();
        solicitudIA.setConsulta(solicitudIADTO.getConsulta());
        solicitudIA.setRespuesta(solicitudIADTO.getRespuesta());
        solicitudIA.setTokensConsumidos(solicitudIADTO.getTokensConsumidos());
        solicitudIA.setFecha(new Date());

        // Asumimos que tienes un método para obtener al Usuario
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        solicitudIA.setUsuario(usuario);

        return solicitudIARepository.save(solicitudIA);
    }

    // Obtener todas las solicitudes de IA de un usuario
    public List<SolicitudIADTO> obtenerSolicitudesPorUsuario(Long usuarioId) {
        List<SolicitudIA> solicitudes = solicitudIARepository.findByUsuarioId(usuarioId);
        return solicitudes.stream()
                .map(solicitud -> {
                    SolicitudIADTO dto = new SolicitudIADTO();
                    dto.setConsulta(solicitud.getConsulta());
                    dto.setRespuesta(solicitud.getRespuesta());
                    dto.setTokensConsumidos(solicitud.getTokensConsumidos());
                    dto.setFecha(solicitud.getFecha());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private Usuario obtenerUsuarioPorId(Long usuarioId) {
        // Lógica para obtener el Usuario por su ID
        // Este método debe ser implementado para obtener al usuario desde la base de datos
        return new Usuario(); // Esta es una implementación simulada
    }
}
