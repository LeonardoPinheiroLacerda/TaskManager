package com.leonardo.taskmanager.resources;

import com.leonardo.taskmanager.dtos.ClassificationDTO;
import com.leonardo.taskmanager.services.ClassificationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "/api/v1/classifications", tags = "Classificações", description = "Gerência classificações")
@RestController
@RequestMapping("/api/v1/classifications")
public class ClassificationResource {
    
    private final ClassificationService service;

    public ClassificationResource(ClassificationService service){
        this.service = service;
    }

    @ApiOperation(
        value = "Insere uma nova classificação no sistema.",
        notes = "Apenas usuários com permissão de ADMIN tem acesso à este endpoint.")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ClassificationDTO> insertClassification(@ApiParam(value = "Classificação que será persistida no banco. O Id é gerado automaticamente.", required = true) @RequestBody ClassificationDTO dto) {
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
