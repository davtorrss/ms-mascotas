package com.vetnova.ms_mascotas.service;

import com.vetnova.ms_mascotas.model.Mascota;
import com.vetnova.ms_mascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public Mascota registrar(Mascota mascota) {
        // Aquí podrías agregar lógica adicional si fuera necesario
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listar() {
        return mascotaRepository.findAll();
    }

    public Mascota actualizar(Long id, Mascota nuevosDatos) {
        return mascotaRepository.findById(id).map(m -> {
            m.setNombre(nuevosDatos.getNombre());
            m.setEspecie(nuevosDatos.getEspecie());
            m.setRaza(nuevosDatos.getRaza());
            m.setEdad(nuevosDatos.getEdad()); // Soporta Double (ej: 0.5)
            m.setDuenoId(nuevosDatos.getDuenoId());
            return mascotaRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("El ID de la mascota no existe");
        }
        mascotaRepository.deleteById(id);
    }
}