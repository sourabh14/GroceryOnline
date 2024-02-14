package com.example.qpassessment.security.controller;

import com.example.qpassessment.security.dao.request.SignInRequest;
import com.example.qpassessment.security.dao.request.SignUpRequest;
import com.example.qpassessment.security.dao.response.JwtAuthenticationResponse;
import com.example.qpassessment.security.exception.UserAlreadyExistsAuthenticationException;
import com.example.qpassessment.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request)
            throws UserAlreadyExistsAuthenticationException {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}
