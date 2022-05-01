package com.leonardo.taskmanager.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.leonardo.taskmanager.dtos.PasswordDTO;
import com.leonardo.taskmanager.dtos.RoleDTO;
import com.leonardo.taskmanager.dtos.UserDTO;
import com.leonardo.taskmanager.dtos.UserWithPasswordDTO;
import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.model.enums.Role;
import com.leonardo.taskmanager.repositories.UserRepository;
import com.leonardo.taskmanager.services.utils.SafeRepositoryOperationsService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@CacheConfig(cacheNames = "user")
@Service
public class UserService {
    
    private final UserRepository repository;
    private final SafeRepositoryOperationsService<User, Integer> safeOpsService;
    private final PasswordEncoder passwordEncoder;
    
    @CacheEvict(allEntries = true)
    public UserDTO save(UserWithPasswordDTO dto){  
        User obj = new User(dto.getName(), dto.getLastName(), dto.getEmail(), dto.getTelephoneNumber(), dto.getUsername());
        
        Set<Role> roles = new HashSet<>();
        roles.add(Role.COMMON);

        obj.setRoles(roles);
        obj.setPassword(passwordEncoder.encode(dto.getPassword()));

        obj = safeOpsService.safeSave(repository, obj);
        return obj.toDTO();
    }

    @CacheEvict(allEntries = true)
    public UserDTO update(UserDTO dto){
        User obj = safeOpsService.safeFindById(repository,dto.getId());

        obj.setName(dto.getName());
        obj.setLastName(dto.getLastName());
        obj.setEmail(dto.getEmail());
        obj.setTelephoneNumber(dto.getTelephoneNumber());
        obj.setUsername(dto.getUsername());

        obj = safeOpsService.safeSave(repository, obj);
        return obj.toDTO();
    }

    @CacheEvict(allEntries = true)
    public void updatePassword(Integer id, PasswordDTO password){
        User obj = safeOpsService.safeFindById(repository, id);
        obj.setPassword(passwordEncoder.encode(password.getPassword()));
        safeOpsService.safeSave(repository, obj);
    }

    @CacheEvict(allEntries = true)
    public void updatePassword(User user, PasswordDTO password){
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        safeOpsService.safeSave(repository, user);
    }

    @CacheEvict(allEntries = true)
    public void updateRoles(Integer id, Set<RoleDTO> roles){
        User obj = safeOpsService.safeFindById(repository, id);

        Set<Role> objs = roles.stream()
            .map(r -> Role.valueOf(r.getRole().name()))
            .collect(Collectors.toSet());

        obj.setRoles(objs);
        safeOpsService.safeSave(repository, obj);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id){
        User obj = safeOpsService.safeFindById(repository, id);
        repository.delete(obj);        
    }

    @Cacheable(key = "#id")
    public UserDTO findById(Integer id){
        User obj = safeOpsService.safeFindById(repository, id);
        return obj.toDTO();
    }

    @Cacheable
    public List<UserDTO> findAll(){
        List<UserDTO> dtos = repository.findAll()
            .stream()
            .map((classification) -> classification.toDTO())
            .collect(Collectors.toList());

        return dtos;
    }

}
