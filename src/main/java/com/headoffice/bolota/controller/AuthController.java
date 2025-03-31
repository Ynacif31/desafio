package com.headoffice.bolota.controller;

import com.headoffice.bolota.dto.AuthRequest;
import com.headoffice.bolota.dto.AuthResponse;
import com.headoffice.bolota.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        if (authRequest.username() == null || authRequest.password() == null) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Credenciais inválidas"));
        }

        AuthResponse response = authService.authenticate(
                authRequest.username(),
                authRequest.password()
        );

        if (!response.authenticated()) {
            return ResponseEntity.status(401).body(response);
        }

        return ResponseEntity.ok(response);
    }
}