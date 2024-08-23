package com.camposeduardo.generator.service;


import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public boolean isTokenEmpty(String token) {
        return token == null || token.isBlank();
    }
}
