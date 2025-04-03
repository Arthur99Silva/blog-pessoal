package com.blogpessoal.blog_pessoal.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // Coleta as credenciais do usuário a partir dos parâmetros da requisição
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Cria um token de autenticação com as credenciais
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = authResult.getName();
        
        // Gera o token JWT com uma expiração de 1 dia
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, "secret_key")  // Chave secreta - ajuste conforme necessário
                .compact();

        // Adiciona o token no cabeçalho da resposta
        response.addHeader("Authorization", "Bearer " + token);

        // Além do cabeçalho, escreve o token no corpo da resposta (em JSON)
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"Bearer " + token + "\"}");
        response.getWriter().flush();
    }
}