package com.example.groceryonline.security.controller.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorResponse {
    private Integer status;
    private String error;
    private String message;
}
