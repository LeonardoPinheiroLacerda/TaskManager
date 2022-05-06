package com.leonardo.taskmanager.resources;

import java.util.List;

import javax.validation.Valid;

import com.leonardo.taskmanager.dtos.NewTeamDTO;
import com.leonardo.taskmanager.dtos.TeamDTO;
import com.leonardo.taskmanager.services.TeamService;

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

@Api(value = "/api/v1/teams", tags = "Equipes", description = "Gerência equipes")
@RestController
@RequestMapping("/api/v1/teams")
public class TeamResource {

    private final TeamService service;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody @Valid NewTeamDTO dto) {
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamDTO> findById(
            @PathVariable Integer id) {
        TeamDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ResponseEntity<TeamDTO> update(
            @RequestBody @Valid NewTeamDTO dto,
            @PathVariable Integer id) {
        dto.setId(id);
        TeamDTO obj = service.update(dto);
        return ResponseEntity.ok(obj);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
