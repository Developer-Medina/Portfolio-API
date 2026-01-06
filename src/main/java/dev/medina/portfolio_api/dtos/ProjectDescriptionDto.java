package dev.medina.portfolio_api.dtos;


import jakarta.validation.constraints.NotBlank;

public class ProjectDescriptionDto { // essa classe precisa existir para que formatemos a nossa entrada de acordo com o que temos na saida
    // estamos apresentando que, desde o inicio, project possui uma descricao
    // e que essa desc fica dentro de project, essa estrutura deve bater ate no dto
    // se colocasse apenas string em longdesc no project dto, ele esperaria uma string, nao outro objeto
    // com essa classe corrigimos isso, dizendo que dentro de project, ha uma proj desc

    // atributos

    @NotBlank
    private String longDescription;

    // construtores


    public ProjectDescriptionDto() {
    }

    public ProjectDescriptionDto(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
