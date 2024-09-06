package com.proxidevcode.spring_react_ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        // Category avec id: 22 est n'existe pas
        super(String.format("%s with field %s: %s not found.", resourceName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }

    private String fieldName;
    private Object fieldValue;
    private String resourceName;

}
