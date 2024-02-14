package com.example.qpassessment.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return new ResponseEntity<>("Root page", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminPage() {
        return new ResponseEntity<>("This is admin user", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<String> normalUserPage() {
        return new ResponseEntity<>("This is normal user", HttpStatus.OK);
    }


}
