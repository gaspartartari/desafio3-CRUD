package com.learning.desafios.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learning.desafios.DTO.ClientDTO;
import com.learning.desafios.entities.Client;
import com.learning.desafios.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public Optional<ClientDTO> findById(Long id){
        Optional<Client> result = repository.findById(id);
        return result.map(x -> new ClientDTO(x));
    }
}
