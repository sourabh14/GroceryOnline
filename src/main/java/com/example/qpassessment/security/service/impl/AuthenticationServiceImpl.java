package com.example.qpassessment.security.service.impl;

import com.example.qpassessment.security.dao.request.SignInRequest;
import com.example.qpassessment.security.dao.request.SignUpRequest;
import com.example.qpassessment.security.dao.response.JwtAuthenticationResponse;
import com.example.qpassessment.security.entity.Role;
import com.example.qpassessment.security.entity.User;
import com.example.qpassessment.security.exception.UserAlreadyExistsAuthenticationException;
import com.example.qpassessment.security.repository.UserRepository;
import com.example.qpassessment.security.util.JwtTokenUtil;
import com.example.qpassessment.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) throws UserAlreadyExistsAuthenticationException {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsAuthenticationException("User " + request.getUsername() + " already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.ROLE_USER.name())
                .build();
        userRepository.save(user);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
