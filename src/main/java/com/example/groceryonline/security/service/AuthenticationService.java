package com.example.groceryonline.security.service;

import com.example.groceryonline.security.dao.request.SignInRequest;
import com.example.groceryonline.security.dao.request.SignUpRequest;
import com.example.groceryonline.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse signupAdmin(SignUpRequest request);

}
