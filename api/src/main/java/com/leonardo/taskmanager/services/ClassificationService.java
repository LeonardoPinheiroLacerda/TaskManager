package com.leonardo.taskmanager.services;

import java.util.List;
import java.util.stream.Collectors;

import com.leonardo.taskmanager.dtos.ClassificationDTO;
import com.leonardo.taskmanager.model.Classification;
import com.leonardo.taskmanager.repositories.ClassificationRepository;
import com.leonardo.taskmanager.services.utils.SafeRepositoryOperationsService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@CacheConfig(cacheNames = "classification")
@Service
public class ClassificationService {
    
    private final ClassificationRepository repository;
    private final SafeRepositoryOperationsService<Classification, Integer> safeOpsService;
    
    @CacheEvict(allEntries = true)
    public ClassificationDTO save(ClassificationDTO dto){  
        Classification obj = new Classification(dto.getClassification());
        
        obj = safeOpsService.safeSave(repository, obj);

        ClassificationDTO savedObj = new ClassificationDTO(obj.getId(), obj.getClassification());

        return savedObj;
    }

    @CacheEvict(allEntries = true)
    public ClassificationDTO update(ClassificationDTO dto){
        Classification obj = safeOpsService.safeFindById(repository,dto.getId());

        obj.setClassification(dto.getClassification());

        obj = safeOpsService.safeSave(repository, obj);

        ClassificationDTO savedObj = new ClassificationDTO(obj.getId(), obj.getClassification());

        return savedObj;
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id){
        Classification obj = safeOpsService.safeFindById(repository, id);

        repository.delete(obj);        
    }

    @Cacheable(key = "#id")
    public ClassificationDTO findById(Integer id){
        Classification obj = safeOpsService.safeFindById(repository, id);

        ClassificationDTO dto = new ClassificationDTO(obj.getId(), obj.getClassification());

        return dto;
    }

    @Cacheable
    public List<ClassificationDTO> findAll(){
        
        List<ClassificationDTO> dtos = repository.findAll()
            .stream()
            .map((classification) -> new ClassificationDTO(classification.getId(), classification.getClassification()))
            .collect(Collectors.toList());

        return dtos;
        
    }

}
