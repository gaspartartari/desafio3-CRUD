package com.learning.desafios.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learning.desafios.DTO.ClientDTO;
import com.learning.desafios.entities.Client;
import com.learning.desafios.repositories.ClientRepository;
import com.learning.desafios.services.exceptions.ResourceNotfoundException;

import jakarta.persistence.EntityNotFoundException;



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
    public ClientDTO findById(Long id){
        Client result = repository.findById(id).orElseThrow(() -> new ResourceNotfoundException("Resource not found"));
        return new ClientDTO(result);
    }

    @Transactional
    public ClientDTO insert (ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } 
        catch (EntityNotFoundException e) {
            throw new ResourceNotfoundException("Entity not found");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id))
            throw new ResourceNotfoundException("Entity not found");
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
