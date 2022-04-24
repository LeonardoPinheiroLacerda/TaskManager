package com.leonardo.taskmanager.resources;

import javax.validation.Valid;

import com.leonardo.taskmanager.dtos.ClassificationDTO;
import com.leonardo.taskmanager.services.ClassificationService;

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
    public ResponseEntity<Void> insertClassification(
        @ApiParam(value = "Classificação que será persistida no banco. O Id é gerado automaticamente.", required = true) 
        @RequestBody 
        @Valid ClassificationDTO dto
        ) {
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    } 

    @ApiOperation(
        value = "Retorna a classificação e id informado."
    )
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClassificationDTO> findById(
        @ApiParam(value = "Id da classificação que deseja receber.", required = true) 
        @PathVariable Integer id 
        ) {
        ClassificationDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }


    @ApiOperation(
        value = "Remove o registro de uma classificação.", 
        notes = "Apenas usuários com permissão de ADMIN tem acesso à este endpoint")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
        @ApiParam(value = "Id da classificação que deve ser deletada.") 
        @PathVariable Integer id 
        ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @ApiOperation(
        value = "Atualiza um registro de classificação"
    )
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClassificationDTO> update(
        @ApiParam(value = "Dados que serão atualizados no novo registro.") 
        @RequestBody 
        @Valid ClassificationDTO dto, 
        @ApiParam(value = "Id da classificação que será atualizada.") 
        @PathVariable Integer id
        ) {
        dto.setId(id);
        ClassificationDTO obj = service.update(dto);
        return ResponseEntity.ok(obj);
    }

}
