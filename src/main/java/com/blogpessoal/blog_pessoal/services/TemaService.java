package com.blogpessoal.blog_pessoal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.blog_pessoal.model.Tema;
import com.blogpessoal.blog_pessoal.repository.TemaRepository;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    // Método para salvar um novo tema
    public Tema salvarTema(Tema tema) {
        return temaRepository.save(tema);
    }

    // Método para buscar todos os temas
    public List<Tema> buscarTodosTemas() {
        return temaRepository.findAll();
    }

    // Método para buscar um tema por nome
    public List<Tema> buscarTemaPorNome(String nome) {
        return temaRepository.findByNome(nome);
    }

    // Método para buscar um tema por ID
    public Optional<Tema> buscarTemaPorId(Long id) {
        return temaRepository.findById(id);
    }

    // Método para excluir um tema
    public void excluirTema(Long id) {
        temaRepository.deleteById(id);
    }
}
