package com.project.eventxperience.exceptions;

import java.util.List;

public class ApiErrorException {
    private final List<String> errors;

    public ApiErrorException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
