package com.blogpessoal.blog_pessoal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "senha_teste";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Senha codificada: " + encodedPassword);
    }
}

