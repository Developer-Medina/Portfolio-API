package dev.medina.portfolio_api.services;

import dev.medina.portfolio_api.dtos.ProjectDto;
import dev.medina.portfolio_api.entities.Project;
import dev.medina.portfolio_api.entities.ProjectDescription;
import dev.medina.portfolio_api.enums.Category;
import dev.medina.portfolio_api.repositories.ProjectRepository;
import dev.medina.portfolio_api.services.exceptions.InvalidDateException;
import dev.medina.portfolio_api.services.exceptions.ProjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    // constantes

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public final LocalDate dataMinima = LocalDate.parse("01/01/2020", formatter);
    LocalDate dataMaxima = LocalDate.now();

    @Autowired
    private ProjectRepository projectRepository;


    // get

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        Optional<Project> temporaryProject = projectRepository.findById(id);
        return temporaryProject.orElseThrow(() -> new ProjectNotFoundException(id));
    }


    // post

    public Project insertProject(ProjectDto dto) {

        Project temporaryProj = new Project();
        ProjectDescription temporaryDesc = new ProjectDescription();

        temporaryProj.setVisible(dto.getVisible());
        temporaryProj.setImgUrl((dto.getImgUrl()));
        temporaryProj.setName(dto.getName());
        temporaryProj.setSubtitle(dto.getSubtitle());
        temporaryProj.setCreationDate(LocalDate.parse(dto.getCreationDate(), formatter));

        // validando data:
        if(temporaryProj.getCreationDate().isBefore(dataMinima) || temporaryProj.getCreationDate().isAfter(dataMaxima)) {
            throw new InvalidDateException("The date you entered is invalid.");
        }

        temporaryProj.setCategory(Category.valueOf(dto.getCategory()));
        temporaryProj.setGithubUrl(dto.getGithubUrl());

        temporaryDesc.setLongDescription(dto.getProjectDescription().getLongDescription());

        temporaryProj.setProjectDescription(temporaryDesc);
        temporaryDesc.setProject(temporaryProj);

        return projectRepository.save(temporaryProj);

    }


    // update

    public Project updateProject(Long id, ProjectDto dto) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        project.setVisible(dto.getVisible());
        project.setImgUrl(dto.getImgUrl());
        project.setName(dto.getName());
        project.setSubtitle(dto.getSubtitle());

        // validando data:
        LocalDate validationDate = LocalDate.parse(dto.getCreationDate(), formatter);
        if(validationDate.isBefore(dataMinima) || validationDate.isAfter(dataMaxima)) {
            throw new InvalidDateException("The date you entered is invalid.");
        }

        project.setCreationDate(validationDate);
        project.setCategory(Category.valueOf(dto.getCategory()));
        project.setGithubUrl(dto.getGithubUrl());

        project.getProjectDescription()
                .setLongDescription(dto.getProjectDescription().getLongDescription());

        return projectRepository.save(project);
    }


    // delete

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }

        projectRepository.deleteById(id);
    }

}
