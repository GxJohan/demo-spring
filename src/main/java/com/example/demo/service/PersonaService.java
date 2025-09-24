package com.example.demo.service;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona update(Long id, Persona persona) {
        persona.setId(id);
        return personaRepository.save(persona);
    }

    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }
}