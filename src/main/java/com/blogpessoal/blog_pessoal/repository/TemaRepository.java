package com.blogpessoal.blog_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.blog_pessoal.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

    // Consultar temas pelo nome
    List<Tema> findByNome(String nome);
}
