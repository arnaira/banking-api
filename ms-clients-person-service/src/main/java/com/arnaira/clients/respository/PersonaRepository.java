package com.arnaira.clients.respository;

import com.arnaira.clients.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findById(Long Id);
}
