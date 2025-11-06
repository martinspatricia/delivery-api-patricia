package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Implementar buscas por e-mail
    Optional<Cliente> findByEmail(String email);

    // Implementar buscas por status ativo
    List<Cliente> findByAtivo(boolean ativo);
}
