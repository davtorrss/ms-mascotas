package com.vetnova.ms_mascotas.controller;

import com.vetnova.ms_mascotas.model.Mascota;
import com.vetnova.ms_mascotas.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    @Autowired private MascotaService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Mascota mascota) { return ResponseEntity.ok(service.guardarMascota(mascota)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) { service.eliminarMascota(id); return ResponseEntity.ok("Mascota eliminada"); }
}