package com.leonardo.taskmanager.services;

import com.leonardo.taskmanager.dtos.ClassificationDTO;
import com.leonardo.taskmanager.model.Classification;
import com.leonardo.taskmanager.repositories.ClassificationRepository;
import com.leonardo.taskmanager.services.utils.SafeRepositoryOperationsService;

import org.springframework.stereotype.Service;

@Service
public class ClassificationService {
    
    private final ClassificationRepository repository;
    private final SafeRepositoryOperationsService<Classification, Integer> safeOpsService;


    //Criar uma operação segura para inserir dados no banco
    public ClassificationService(
        ClassificationRepository classificationRepository, 
        SafeRepositoryOperationsService<Classification, Integer> safeOpsService
    ){
        this.repository = classificationRepository;
        this.safeOpsService = safeOpsService;
    }

    public ClassificationDTO save(ClassificationDTO dto){  
        Classification obj = new Classification(dto.getClassification());
        
        obj = safeOpsService.safeSave(repository, obj);

        ClassificationDTO savedObj = new ClassificationDTO(obj.getId(), obj.getClassification());

        return savedObj;
    }

    public ClassificationDTO update(ClassificationDTO dto){
        Classification obj = safeOpsService.safeFindById(repository,dto.getId());

        obj.setClassification(dto.getClassification());

        obj = safeOpsService.safeSave(repository, obj);

        ClassificationDTO savedObj = new ClassificationDTO(obj.getId(), obj.getClassification());

        return savedObj;
    }

    public void delete(Integer id){
        Classification obj = safeOpsService.safeFindById(repository, id);

        repository.delete(obj);        
    }

    public ClassificationDTO findById(Integer id){
        Classification obj = safeOpsService.safeFindById(repository, id);

        ClassificationDTO dto = new ClassificationDTO(obj.getId(), obj.getClassification());

        return dto;
    }

}
