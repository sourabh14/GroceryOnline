package com.example.qpassessment.grocery.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {
    private HttpStatus status;

    public ApplicationException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
