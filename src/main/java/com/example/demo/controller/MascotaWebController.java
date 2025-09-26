package com.example.demo.controller;

import com.example.demo.model.Mascota;
import com.example.demo.service.MascotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaWebController {

    private final MascotaService mascotaService;

    public MascotaWebController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public String listMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.findAll());
        return "mascotas/list";
    }

    @GetMapping("/new")
    public String newMascotaForm(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "mascotas/form";
    }

    @GetMapping("/{id}/edit")
    public String editMascotaForm(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaService.findById(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "mascotas/form";
        }
        return "redirect:/mascotas";
    }

    @PostMapping
    public String saveMascota(@ModelAttribute Mascota mascota) {
        if (mascota.getNombre() != null && !mascota.getNombre().trim().isEmpty() &&
            mascota.getTipo() != null && !mascota.getTipo().trim().isEmpty()) {

            if (mascota.getId() != null) {
                mascotaService.update(mascota.getId(), mascota);
            } else {
                mascotaService.save(mascota);
            }
        }
        return "redirect:/mascotas";
    }

    @PostMapping("/{id}/delete")
    public String deleteMascota(@PathVariable Long id) {
        mascotaService.deleteById(id);
        return "redirect:/mascotas";
    }

    @GetMapping("/{id}")
    public String viewMascota(@PathVariable Long id, Model model) {
        Optional<Mascota> mascota = mascotaService.findById(id);
        if (mascota.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            return "mascotas/view";
        }
        return "redirect:/mascotas";
    }
}