package com.blogpessoal.blog_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogpessoal.blog_pessoal.model.Postagem;

@Repository 
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    // Consultar postagens pelo título
    List<Postagem> findByTitulo(String titulo);
    
    // Consultar postagens por parte do título
    @Query("SELECT p FROM Postagem p WHERE p.titulo LIKE %:titulo%")
    List<Postagem> findByTituloContaining(String titulo);
    
    // Consultar postagens de um usuário específico
    List<Postagem> findByUsuarioId(Long usuarioId);
}
