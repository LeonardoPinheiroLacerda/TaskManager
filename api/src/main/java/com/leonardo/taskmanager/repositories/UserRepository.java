package com.leonardo.taskmanager.repositories;

import com.leonardo.taskmanager.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
}
