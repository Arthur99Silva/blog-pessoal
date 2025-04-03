package com.blogpessoal.blog_pessoal.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.blog_pessoal.model.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Endpoint de Login (onde o JWT será gerado)
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // A autenticação e geração de token serão feitas pelos filtros definidos acima
        return "Login bem-sucedido. Token gerado!";
    }
}