package com.blogpessoal.blog_pessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity  // Indica que essa classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados
public class Usuario {

    @Id  // Define o campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Gera o valor da chave primária automaticamente (auto incremento)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    // Construtores, getters e setters
    public Usuario() {}

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
