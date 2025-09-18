package com.Disney.DisneyApp.controller;

import com.Disney.DisneyApp.models.DTO.AuthRequest;
import com.Disney.DisneyApp.models.DTO.AuthResponse;
import com.Disney.DisneyApp.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "00-Auth", description = "Endpoints de autenticación")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok(response);
    }
}
