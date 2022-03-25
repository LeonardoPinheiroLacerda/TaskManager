package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
