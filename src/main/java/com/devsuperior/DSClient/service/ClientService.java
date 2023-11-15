package com.devsuperior.DSClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DSClient.dto.ClientDTO;
import com.devsuperior.DSClient.entities.Client;
import com.devsuperior.DSClient.repositores.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).get();
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

        Client entity = repository.getReferenceById(id);
        copyDtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);

    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDtoEntity(ClientDTO dto, Client entity) {

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}