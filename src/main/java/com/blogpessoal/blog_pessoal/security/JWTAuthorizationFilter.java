package com.blogpessoal.blog_pessoal.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain
    ) throws IOException, ServletException {
        
        String header = request.getHeader("Authorization");
        
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String token = header.replace("Bearer ", "");
            
            // Validação do token com a chave correta
            Claims claims = Jwts.parserBuilder()
                .setSigningKey("4e242f5aa0606da2e36bd2a204cd8200a50aebae8d166567d5a317fbf3233ab19ad36d1c8eee3197d0d81e5b265d0b9aca2284711a0ec8e8e2883def9861ecaa5d1f4285f52e2b73df2c947f02d35198d25d5322091d1306bbab70b3cde55b31622f3b77a6d7f6cf14a83ac6e6a3e3a3b675396b96f10fe4a1b2350d48b7aef0424444ac40aaca4b2e49fd3bdb0dc62bdd790f5223073c4f88df10ba0c902860c7989b785a8c889958277a481b059be1d0541da1ab8342e130bb4b3fa44a37e6162672a19dc62e60388b04749dec9e9864b0e662fc52036834935726869fd4ee9ffebe78fc22e81b8506b7b8e60ab276dc7cafc3d3b96811d2183ab74832fe0231ac2e83cc0477498b5da8c7a9f4d7183193f1b2dfc7cab275e7ff64487918735b43595025d7a650edc38bc379d11a6f116c061fe91a878fd375d613fe6276d55aa7bb80c22f865b9b48e8a3d1d72e83686eb4191a2a8f1743848c30710da6f80cbb2fcf3225f268885ad14cc98ebb01fef2bc6b512ea181e429d03d2e1ab126a9c4f878c54c8c320a5453b44e4b972aa95ec13837f7887f19c2cd0fdeae39af1b842497e01d9a993c9adc89322395c647cbc707cbe330ff7043ccbe8ec04781437f6bd6e659d4db18dfb52a4b229b9862c0c181c4bb8b438404ae5130946661649a0e6976d5887b3b8507c06eb296120259217f8cd1d6e94552233ba797cd68")
                .build()
                .parseClaimsJws(token)
                .getBody();

            // Cria contexto de autenticação
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), 
                null, 
                Collections.emptyList()
            );
            
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}