package com.blogpessoal.blog_pessoal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.blog_pessoal.model.Postagem;
import com.blogpessoal.blog_pessoal.repository.PostagemRepository;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    // Método para salvar uma nova postagem
    public Postagem salvarPostagem(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    // Método para buscar todas as postagens
    public List<Postagem> buscarTodasPostagens() {
        return postagemRepository.findAll();
    }

    // Método para buscar postagens por título
    public List<Postagem> buscarPostagensPorTitulo(String titulo) {
        return postagemRepository.findByTituloContaining(titulo);
    }

    // Método para buscar postagens de um usuário específico
    public List<Postagem> buscarPostagensPorUsuario(Long usuarioId) {
        return postagemRepository.findByUsuarioId(usuarioId);
    }

    // Método para buscar uma postagem por ID
    public Optional<Postagem> buscarPostagemPorId(Long id) {
        return postagemRepository.findById(id);
    }
}
