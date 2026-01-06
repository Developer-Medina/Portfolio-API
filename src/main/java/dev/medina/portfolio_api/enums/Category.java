package dev.medina.portfolio_api.enums;

import dev.medina.portfolio_api.enums.exceptions.CategoryException;

public enum Category {

    FRONT_END(1),
    BACK_END(2),
    FULLSTACK(3);

    private int code;

    private Category(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Category valueOf(int code) {
        for(Category category : Category.values()) {
            if(category.getCode() == code) {
                return category;
            }
        }

        throw new CategoryException(code);
    }

}