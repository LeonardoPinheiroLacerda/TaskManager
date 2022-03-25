package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>{
    
}