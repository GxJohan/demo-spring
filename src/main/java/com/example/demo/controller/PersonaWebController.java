package com.example.demo.controller;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/personas")
public class PersonaWebController {

    private final PersonaService personaService;

    public PersonaWebController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public String listPersonas(Model model) {
        model.addAttribute("personas", personaService.findAll());
        return "personas/list";
    }

    @GetMapping("/new")
    public String newPersonaForm(Model model) {
        model.addAttribute("persona", new Persona());
        return "personas/form";
    }

    @GetMapping("/{id}/edit")
    public String editPersonaForm(@PathVariable Long id, Model model) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            return "personas/form";
        }
        return "redirect:/personas";
    }

    @PostMapping
    public String savePersona(@ModelAttribute Persona persona) {
        if (persona.getNombre() != null && !persona.getNombre().trim().isEmpty() &&
            persona.getApellido() != null && !persona.getApellido().trim().isEmpty()) {

            if (persona.getId() != null) {
                personaService.update(persona.getId(), persona);
            } else {
                personaService.save(persona);
            }
        }
        return "redirect:/personas";
    }

    @PostMapping("/{id}/delete")
    public String deletePersona(@PathVariable Long id) {
        personaService.deleteById(id);
        return "redirect:/personas";
    }

    @GetMapping("/{id}")
    public String viewPersona(@PathVariable Long id, Model model) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            return "personas/view";
        }
        return "redirect:/personas";
    }
}