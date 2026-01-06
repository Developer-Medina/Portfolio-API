package dev.medina.portfolio_api.configuration;

import dev.medina.portfolio_api.entities.Project;
import dev.medina.portfolio_api.entities.ProjectDescription;
import dev.medina.portfolio_api.enums.Category;
import dev.medina.portfolio_api.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("seeding")
public class DataBaseSeeding implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {

        Project p1 = new Project();
        p1.setVisible(true);
        p1.setName("Portfolio antigo");
        p1.setSubtitle("Just another portfolio");
        p1.setCreationDate(LocalDate.parse("2025-10-30"));
        p1.setCategory(Category.FRONT_END);
        p1.setGithubUrl(" ");
        p1.setImgUrl(" ");

        // criando project desc
        ProjectDescription d1 = new ProjectDescription();
        d1.setLongDescription("A delightful front-end portfolio");

        // 3associando ambos
        p1.setProjectDescription(d1);
        d1.setProject(p1);

        // salvando
        projectRepository.save(p1);

    }
}
