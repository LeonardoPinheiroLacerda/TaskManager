package com.leonardo.taskmanager.repositories;

import java.util.Optional;

import com.leonardo.taskmanager.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
 
    @Query("SELECT obj FROM User obj WHERE obj.username = ?1")
    public Optional<User> findByUsername(String username);

}
