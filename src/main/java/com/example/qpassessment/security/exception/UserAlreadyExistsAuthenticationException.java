package com.example.qpassessment.security.exception;

import javax.naming.AuthenticationException;

public class UserAlreadyExistsAuthenticationException extends AuthenticationException {

    public UserAlreadyExistsAuthenticationException(final String msg) {
        super(msg);
    }

}
