package com.vetnova.ms_mascotas.service;

import com.vetnova.ms_mascotas.model.Mascota;
import com.vetnova.ms_mascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.List;

@Service
public class MascotaService {
    @Autowired private MascotaRepository repository;
    @Autowired private WebClient.Builder webClientBuilder;

    public Mascota guardarMascota(Mascota mascota) {
        validarDueno(mascota.getDuenoId());
        return repository.save(mascota);
    }

    public void eliminarMascota(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Mascota no encontrada");
        repository.deleteById(id);
    }

    private void validarDueno(Long duenoId) {
        try {
            webClientBuilder.build().get().uri("http://localhost:8082/api/duenos/" + duenoId).retrieve().bodyToMono(Object.class).block();
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("El dueño con ID " + duenoId + " no existe.");
        } catch (Exception e) {
            throw new RuntimeException("Servicio de dueños caído.");
        }
    }
}