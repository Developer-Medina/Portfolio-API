package dev.medina.portfolio_api.enums.exceptions;

import dev.medina.portfolio_api.enums.Category;

import java.util.Arrays;

public class CategoryException extends RuntimeException {
    public CategoryException(int code) {
        super("Invalid category code: " + code + ". Valid values are 1 (FRONT_END), 2 (BACK_END), 3 (FULLSTACK).");
    }
}
