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

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }
}