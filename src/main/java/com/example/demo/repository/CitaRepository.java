package com.example.demo.repository;

import com.example.demo.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Query method para buscar citas por mascota
    List<Cita> findByMascotaId(Long mascotaId);

    // Query method para buscar por estado
    List<Cita> findByEstado(String estado);

    // Query method para buscar por mascota y estado
    List<Cita> findByMascotaIdAndEstado(Long mascotaId, String estado);
}
