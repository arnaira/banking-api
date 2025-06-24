package com.arnaira.clients.respository;


import com.arnaira.clients.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Optional<Cliente> findById(Long Id);
    boolean existsById(Long Id);
    boolean existsByClienteId(String clienteId);
}
