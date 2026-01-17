package dev.medina.portfolio_api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectDto {

    // atributos

    @NotNull
    private Boolean visible;
    @NotBlank
    private String imgUrl;
    @NotBlank
    private String name;
    @NotBlank
    private String subtitle;
    @NotBlank
    private String creationDate;
    @NotNull
    private Integer category;
    @NotBlank
    private String githubUrl;
    @NotNull
    @Valid // isso para validar esse dto internamente
    private ProjectDescriptionDto projectDescription;


    // construtores


    public ProjectDto() {
    }

    public ProjectDto(Boolean visible, String imgUrl, String name, String subtitle, String creationDate, Integer category, String githubUrl, ProjectDescriptionDto projectDescription) {
        this.visible = visible;
        this.imgUrl = imgUrl;
        this.name = name;
        this.subtitle = subtitle;
        this.creationDate = creationDate;
        this.category = category;
        this.githubUrl = githubUrl;
        this.projectDescription = projectDescription;
    }

    // getters and setters


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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public ProjectDescriptionDto getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(ProjectDescriptionDto projectDescription) {
        this.projectDescription = projectDescription;
    }
}
