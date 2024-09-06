package com.proxidevcode.spring_react_ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponseApi {
    private String message;
    private Integer errorCode;
    private String error;
    private String ErrorDescription;
    private Set<String> validationErrors;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}
