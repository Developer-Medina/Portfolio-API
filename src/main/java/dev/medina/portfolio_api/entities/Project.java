package dev.medina.portfolio_api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.medina.portfolio_api.enums.Category;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tab_project")
public class Project implements Serializable {

    // constantes e máscaras

    // atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean visible;
    private String imgUrl;
    private String name;
    private String subtitle;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") // assim formatamos tambem a saida da data
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String githubUrl;
    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true) // isso tudo é necessario pro hibernate. Como project é dono do metodo save que usamos, precisa ser aqui. Cascade faz salvar tanto o que salvamos tipo p1 como o que tem nele, ou seja, salvamos a desc junto (ai salva um antes e o outro depois)
    private ProjectDescription projectDescription;


    // construtores


    public Project() {
    }

    public Project(Integer id, Boolean visible, String imgUrl, String name, String subtitle, LocalDate creationDate, Category category, String githubUrl, ProjectDescription projectDescription) {
        this.id = id;
        this.visible = visible;
        this.imgUrl = imgUrl;
        this.name = name;
        this.subtitle = subtitle;
        this.creationDate = creationDate;
        this.category = category;
        this.githubUrl = githubUrl;
        this.projectDescription = projectDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(ProjectDescription projectDescription) {
        this.projectDescription = projectDescription;
    }

    // hashCode e equals

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

