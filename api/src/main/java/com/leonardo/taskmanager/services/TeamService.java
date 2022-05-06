package com.leonardo.taskmanager.services;

import java.util.List;
import java.util.stream.Collectors;

import com.leonardo.taskmanager.dtos.NewTeamDTO;
import com.leonardo.taskmanager.dtos.TeamDTO;
import com.leonardo.taskmanager.model.Team;
import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.repositories.TeamRepository;
import com.leonardo.taskmanager.services.utils.SafeRepositoryOperationsService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@CacheConfig(cacheNames = "team")
@Service
public class TeamService {
    
    private final TeamRepository repository;
    private final SecurityService securityService;
    private final SafeRepositoryOperationsService<Team, Integer> safeOpsService;
    
    @CacheEvict(allEntries = true)
    public TeamDTO save(NewTeamDTO dto){  
        Team obj = new Team(dto.getName());

        User user = securityService.getAuthenticatedUser().get();
        obj.setLeader(user);

        obj = safeOpsService.safeSave(repository, obj);
        return obj.toDTO();
    }

    @CacheEvict(allEntries = true)
    public TeamDTO update(NewTeamDTO dto){
        Team obj = safeOpsService.safeFindById(repository,dto.getId());
        obj.setName(dto.getName());
        obj = safeOpsService.safeSave(repository, obj);
        return obj.toDTO();
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id){
        Team obj = safeOpsService.safeFindById(repository, id);
        repository.delete(obj);        
    }

    @Cacheable(key = "#id")
    public TeamDTO findById(Integer id){
        Team obj = safeOpsService.safeFindById(repository, id);
        return obj.toDTO();
    }

    @Cacheable
    public List<TeamDTO> findAll(){
        
        List<TeamDTO> dtos = repository.findAll()
            .stream()
            .map((team) -> team.toDTO())
            .collect(Collectors.toList());

        return dtos;
        
    }

}
