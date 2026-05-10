package com.vetnova.ms_mascotas.controller;

import com.vetnova.ms_mascotas.model.Mascota;
import com.vetnova.ms_mascotas.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @PostMapping("/crear")
    public ResponseEntity<Mascota> crear(@Valid @RequestBody Mascota m) {
        Mascota nueva = mascotaService.guardarMascota(m);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(mascotaService.obtenerTodas());
    }
}