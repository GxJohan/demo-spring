package com.example.demo.controller;

import com.example.demo.model.Mascota;
import com.example.demo.service.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> getAllMascotas() {
        return mascotaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
        return mascotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMascota(@RequestBody Mascota mascota) {
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'nombre' es obligatorio");
        }
        if (mascota.getTipo() == null || mascota.getTipo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'tipo' es obligatorio");
        }
        return ResponseEntity.ok(mascotaService.save(mascota));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        if (mascotaService.findById(id).isPresent()) {
            return ResponseEntity.ok(mascotaService.update(id, mascota));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        if (mascotaService.findById(id).isPresent()) {
            mascotaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}