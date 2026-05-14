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
    public ResponseEntity<?> crear(@Valid @RequestBody Mascota mascota) {
        try {
            Mascota nueva = mascotaService.registrar(mascota);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo de error para que no tire 500
            return new ResponseEntity<>(
                "Error: No se pudo registrar la mascota. Verifique los datos enviados.", 
                HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(mascotaService.listar());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @Valid @RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.actualizar(id, mascota));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
        return ResponseEntity.ok("Mascota eliminada correctamente del sistema.");
    }
}