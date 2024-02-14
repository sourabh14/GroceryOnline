package com.example.qpassessment.security.service;

import com.example.qpassessment.security.dao.request.SignInRequest;
import com.example.qpassessment.security.dao.request.SignUpRequest;
import com.example.qpassessment.security.dao.response.JwtAuthenticationResponse;
import com.example.qpassessment.security.exception.UserAlreadyExistsAuthenticationException;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request) throws UserAlreadyExistsAuthenticationException;

    JwtAuthenticationResponse signin(SignInRequest request);
}
