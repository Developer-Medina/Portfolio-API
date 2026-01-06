package dev.medina.portfolio_api.repositories;

import dev.medina.portfolio_api.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
