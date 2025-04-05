package com.blogpessoal.blog_pessoal.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogpessoal.blog_pessoal.model.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            String username = loginRequest.getUsername();

            logger.info("Tentativa de login iniciada para o usuário: {}", username);

            UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword());
            
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            logger.error("Erro ao processar login: {}", e.getMessage());
            throw new RuntimeException("Falha na autenticação", e);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain, 
        Authentication authResult
    ) throws IOException, ServletException {
        String username = authResult.getName();

        String token = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS512, "4e242f5aa0606da2e36bd2a204cd8200a50aebae8d166567d5a317fbf3233ab19ad36d1c8eee3197d0d81e5b265d0b9aca2284711a0ec8e8e2883def9861ecaa5d1f4285f52e2b73df2c947f02d35198d25d5322091d1306bbab70b3cde55b31622f3b77a6d7f6cf14a83ac6e6a3e3a3b675396b96f10fe4a1b2350d48b7aef0424444ac40aaca4b2e49fd3bdb0dc62bdd790f5223073c4f88df10ba0c902860c7989b785a8c889958277a481b059be1d0541da1ab8342e130bb4b3fa44a37e6162672a19dc62e60388b04749dec9e9864b0e662fc52036834935726869fd4ee9ffebe78fc22e81b8506b7b8e60ab276dc7cafc3d3b96811d2183ab74832fe0231ac2e83cc0477498b5da8c7a9f4d7183193f1b2dfc7cab275e7ff64487918735b43595025d7a650edc38bc379d11a6f116c061fe91a878fd375d613fe6276d55aa7bb80c22f865b9b48e8a3d1d72e83686eb4191a2a8f1743848c30710da6f80cbb2fcf3225f268885ad14cc98ebb01fef2bc6b512ea181e429d03d2e1ab126a9c4f878c54c8c320a5453b44e4b972aa95ec13837f7887f19c2cd0fdeae39af1b842497e01d9a993c9adc89322395c647cbc707cbe330ff7043ccbe8ec04781437f6bd6e659d4db18dfb52a4b229b9862c0c181c4bb8b438404ae5130946661649a0e6976d5887b3b8507c06eb296120259217f8cd1d6e94552233ba797cd68")
            .compact();

        logger.info("LOGIN BEM-SUCEDIDO - Usuário: {}", username);
        logger.debug("Token gerado: {}", token);

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write("{\"token\": \"Bearer " + token + "\"}");
    }
}