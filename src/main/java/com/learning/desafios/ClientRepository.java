package com.learning.desafios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.desafios.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
