package com.leonardo.taskmanager.resources;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.leonardo.taskmanager.dtos.PasswordDTO;
import com.leonardo.taskmanager.dtos.RoleDTO;
import com.leonardo.taskmanager.dtos.UserDTO;
import com.leonardo.taskmanager.dtos.UserWithPasswordDTO;
import com.leonardo.taskmanager.services.SecurityService;
import com.leonardo.taskmanager.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@Api(value = "/api/v1/users", tags = "Usuários", description = "Gerência usuários")
@RestController
@RequestMapping("/api/v1/users")
public class UserResource {
    
    private final UserService service;
    private final SecurityService securityService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/logged")
    public ResponseEntity<UserDTO> logged(){
        UserDTO dto = securityService.getAuthenticatedUser().orElse(null).toDTO();
        if(dto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(securityService.getAuthenticatedUser().orElse(null).toDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> save(@RequestBody @Valid UserWithPasswordDTO dto){
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO dto, @PathVariable Integer id){
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Void> updateRoles(@RequestBody Set<RoleDTO> roles, @PathVariable Integer id){
        service.updateRoles(id, roles);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid PasswordDTO password, @PathVariable Integer id){
        service.updatePassword(id, password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
