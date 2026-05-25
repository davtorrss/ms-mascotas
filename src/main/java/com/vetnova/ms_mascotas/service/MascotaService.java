package com.vetnova.ms_mascotas.service;

import com.vetnova.ms_mascotas.model.Mascota;
import com.vetnova.ms_mascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mascota guardarMascota(Mascota mascota) {
        // Validar si el dueño existe en ms-duenos
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/duenos/" + mascota.getDuenoId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block(); // Esto espera la respuesta
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("El dueño con ID " + mascota.getDuenoId() + " no existe en el sistema.");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo conectar con el servicio de dueños. ¿Está encendido?");
        }

        // Si llegó hasta aquí, el dueño existe, guardamos la mascota
        return repository.save(mascota);
    }
}