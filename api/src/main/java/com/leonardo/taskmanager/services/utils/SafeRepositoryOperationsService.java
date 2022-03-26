package com.leonardo.taskmanager.services.utils;

import com.leonardo.taskmanager.exceptions.ObjectNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SafeRepositoryOperationsService<T extends Object, TID> {
    
    public T safeFindById(JpaRepository<T, TID> repository, TID id) throws ObjectNotFoundException{
        
        T obj = repository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("O objeto de Id " + id + " não pode ser localizado."));

        return obj;

    }

}