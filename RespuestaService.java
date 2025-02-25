package com.exemple.forohub.service;

import com.exemple.forohub.DTO.DatosRegistroRespuestas;
import com.exemple.forohub.model.Respuesta;
import com.exemple.forohub.model.Topico;
import com.exemple.forohub.model.Usuario;
import com.exemple.forohub.repository.IRespuestaRepository;
import com.exemple.forohub.repository.ITopicoRepository;
import com.exemple.forohub.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private IRespuestaRepository respuestaRepo;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ITopicoRepository topicoRepo;

    @Transactional
    public Respuesta registrarServicio(DatosRegistroRespuestas datosRegistroRespuesta) {

        Long topicoId = datosRegistroRespuesta.topicoId();
        Topico topico = topicoRepo.findById(topicoId).orElseThrow(() -> new IllegalArgumentException("El curso con ID " + topicoId + " no existe"));

        Long autorId = datosRegistroRespuesta.autorId();
        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new IllegalArgumentException("El autor con ID " + autorId + " no existe."));

        Respuesta respuesta = new Respuesta(datosRegistroRespuesta, autor, topico);
        return respuestaRepo.save(respuesta);
    }

    public Page<Respuesta> getRespuestasPorTopico(Long topicoId, Pageable pageable) {
        return respuestaRepo.findByTopicoId(topicoId, pageable);
    }

    public void marcarRespuestaComoSolucion(Long idRespuesta) {
        Respuesta respuesta = respuestaRepo.findById(idRespuesta)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada para el ID: " + idRespuesta));

        respuesta.darRespuestaComoSolucion();

        respuestaRepo.save(respuesta); 
    }

    public void desmarcarRespuestaComoSolucion(Long idRespuesta) {
       Respuesta respuesta = respuestaRepo.findById(idRespuesta)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada para el ID: " + idRespuesta));

        respuesta.desmarcarRespuestaComoSolucion();

        respuestaRepo.save(respuesta); 
    }

}
