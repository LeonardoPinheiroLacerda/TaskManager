package com.leonardo.taskmanager.services;

import java.util.List;
import java.util.stream.Collectors;

import com.leonardo.taskmanager.dtos.ClientDTO;
import com.leonardo.taskmanager.model.Client;
import com.leonardo.taskmanager.repositories.ClientRepository;
import com.leonardo.taskmanager.services.utils.SafeRepositoryOperationsService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
@CacheConfig(cacheNames = "client")
public class ClientService {
    
    private final ClientRepository repository;
    private final SafeRepositoryOperationsService<Client, Integer> safeOpsService;

    @Cacheable
    public List<ClientDTO> findAll(){
        return repository.findAll()
            .stream()
            .map(client -> client.toDTO())
            .collect(Collectors.toList());
    }

    @Cacheable(key = "#id")
    public ClientDTO findById(Integer id){
        Client client = safeOpsService.safeFindById(repository, id);
        return client.toDTO();
    }

    @CacheEvict(allEntries = true)
    public ClientDTO save(ClientDTO dto){
        Client client = new Client(dto.getClient());
        client = safeOpsService.safeSave(repository, client);
        return client.toDTO();
    }

    @CacheEvict(allEntries = true)
    public ClientDTO update(ClientDTO dto){
        Client client = safeOpsService.safeFindById(repository, dto.getId());
        client.setClient(dto.getClient());
        
        client = safeOpsService.safeSave(repository, client);
        return client.toDTO();
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id){
        Client client = safeOpsService.safeFindById(repository, id);
        repository.delete(client);
    }
}
