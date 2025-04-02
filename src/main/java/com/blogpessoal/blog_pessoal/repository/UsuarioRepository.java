package com.blogpessoal.blog_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpessoal.blog_pessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para buscar usuário por username (nome de usuário)
    Usuario findByUsername(String username);
    
    // Método para buscar usuários por nome (usando 'containing' para busca parcial)
    List<Usuario> findByNomeContaining(String nome);
    
    // Método para buscar usuários por email
    List<Usuario> findByEmail(String email);
}
