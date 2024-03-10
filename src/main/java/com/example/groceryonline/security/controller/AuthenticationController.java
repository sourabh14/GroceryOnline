package com.example.groceryonline.security.controller;

import com.example.groceryonline.security.dao.request.SignInRequest;
import com.example.groceryonline.security.dao.request.SignUpRequest;
import com.example.groceryonline.security.dao.response.JwtAuthenticationResponse;
import com.example.groceryonline.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup/user")
    public ResponseEntity<JwtAuthenticationResponse> signup(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));

    }

    @PostMapping("/signup/admin")
    public ResponseEntity<JwtAuthenticationResponse> signupAdmin(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signupAdmin(request));
    }

}
