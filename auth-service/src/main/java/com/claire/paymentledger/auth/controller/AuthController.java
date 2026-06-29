package com.claire.paymentledger.auth.controller;

import com.claire.paymentledger.auth.dto.AuthResponse;
import com.claire.paymentledger.auth.dto.LoginRequest;
import com.claire.paymentledger.auth.dto.RegisterRequest;
import com.claire.paymentledger.auth.dto.RegisterResponse;
import com.claire.paymentledger.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
