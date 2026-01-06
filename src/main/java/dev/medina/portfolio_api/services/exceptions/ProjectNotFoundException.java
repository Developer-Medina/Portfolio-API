package dev.medina.portfolio_api.services.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Object id) {
        super("There is no project with id " + id);
    }
}
