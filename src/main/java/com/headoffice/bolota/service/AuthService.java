package com.headoffice.bolota.service;

import com.headoffice.bolota.dto.AuthResponse;
import com.headoffice.bolota.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        return new AuthResponse(true, "Autenticação bem-sucedida");
                    } else {
                        return new AuthResponse(false, "Senha incorreta");
                    }
                })
                .orElse(new AuthResponse(false, "Usuário não encontrado"));
    }
}