package com.learning.desafios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.desafios.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
