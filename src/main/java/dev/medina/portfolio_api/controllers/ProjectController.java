package dev.medina.portfolio_api.controllers;

import dev.medina.portfolio_api.dtos.ProjectDto;
import dev.medina.portfolio_api.entities.Project;
import dev.medina.portfolio_api.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        List<Project> returnList = projectService.findAll();
        return ResponseEntity.ok().body(returnList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id) {
        Project temporaryProject = projectService.findById(id);
        return ResponseEntity.ok().body(temporaryProject);
    }

    @PostMapping
    public ResponseEntity<Project> save(@Valid @RequestBody ProjectDto dto) { // usamos valid para validar se ele nao vem null
        Project temporaryProject = projectService.insertProject(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(temporaryProject.getId()).toUri();
        return ResponseEntity.created(uri).body(temporaryProject);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @Valid @RequestBody ProjectDto dto) {
        Project temporaryProject = projectService.updateProject(id, dto);
        return ResponseEntity.ok().body(temporaryProject);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}
