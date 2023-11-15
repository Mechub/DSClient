package com.devsuperior.DSClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DSClient.dto.ClientDTO;
import com.devsuperior.DSClient.entities.Client;
import com.devsuperior.DSClient.repositores.ClientRepository;
import com.devsuperior.DSClient.service.exception.DatabaseException;
import com.devsuperior.DSClient.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }
    
    @Transactional(readOnly = true)
    public ClientDTO insert(ClientDTO dto){

        Client entity = new Client();
        copyDtoEntity(dto, entity);      
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
    try{
        Client entity = repository.getReferenceById(id);
        copyDtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
       }
       catch (EntityNotFoundException e){
        throw new ResourceNotFoundException("Recurso não encontrado");
       }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            repository.deleteById(id);            
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }


    private void copyDtoEntity(ClientDTO dto, Client entity) {

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }



}