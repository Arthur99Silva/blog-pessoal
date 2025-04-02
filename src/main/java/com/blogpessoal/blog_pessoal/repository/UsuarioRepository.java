package com.blogpessoal.blog_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogpessoal.blog_pessoal.model.Usuario;

@Repository  // Marcando a interface como um repositório Spring
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consultar usuários por nome exato
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    List<Usuario> findByNome(String nome);
    
    // Consultar usuários por email
    List<Usuario> findByEmail(String email);
    
    // Consultar usuários parcialmente pelo nome (usando LIKE)
    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %:nome%")
    List<Usuario> findByNomeContaining(String nome);
}
