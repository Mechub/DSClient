package com.devsuperior.DSClient.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.DSClient.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
