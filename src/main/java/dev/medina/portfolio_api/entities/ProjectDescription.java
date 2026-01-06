package dev.medina.portfolio_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "projectDescription_tb")
public class ProjectDescription implements Serializable {

    // constantes


    // atributos

    @Id
    private Integer id;
    @Lob
    private String longDescription;


    // associacoes

    @MapsId
    @OneToOne
    @JoinColumn(name = "projectFK")
    @JsonIgnore // evitando lazy loading e loop
    private Project project;


    // construtores

    public ProjectDescription() {
    }

    public ProjectDescription(String longDescription, Project project) {
        this.longDescription = longDescription;
        this.project = project;
    }

    // getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    // hashCode e equals

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDescription that = (ProjectDescription) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
