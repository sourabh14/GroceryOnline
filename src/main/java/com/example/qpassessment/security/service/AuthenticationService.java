package com.example.qpassessment.security.service;

import com.example.qpassessment.security.dao.request.SignInRequest;
import com.example.qpassessment.security.dao.request.SignUpRequest;
import com.example.qpassessment.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse signupAdmin(SignUpRequest request);

}
