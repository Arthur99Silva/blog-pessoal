package com.blogpessoal.blog_pessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.blog_pessoal.model.Postagem;
import com.blogpessoal.blog_pessoal.services.PostagemService;

@RestController
@RequestMapping("/postagens")  // Caminho postagens
public class PostagemController {

    @Autowired
    private PostagemService postagemService; 

    // Endpoint para criar uma nova postagem
    @PostMapping
    public Postagem salvarPostagem(@RequestBody Postagem postagem) {
        return postagemService.salvarPostagem(postagem);
    }

    // Endpoint para listar todas as postagens
    @GetMapping
    public List<Postagem> buscarTodasPostagens() {
        return postagemService.buscarTodasPostagens();
    }

    // Endpoint para buscar postagens pelo título
    @GetMapping("/titulo/{titulo}")
    public List<Postagem> buscarPostagensPorTitulo(@PathVariable String titulo) {
        return postagemService.buscarPostagensPorTitulo(titulo);
    }

    // Endpoint para buscar postagens de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<Postagem> buscarPostagensPorUsuario(@PathVariable Long usuarioId) {
        return postagemService.buscarPostagensPorUsuario(usuarioId);
    }

    // Endpoint para buscar uma postagem por ID
    @GetMapping("/{id}")
    public Optional<Postagem> buscarPostagemPorId(@PathVariable Long id) {
        return postagemService.buscarPostagemPorId(id);
    }
    
    // Endpoint para excluir uma postagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPostagem(@PathVariable Long id) {
        postagemService.excluirPostagem(id);
        return ResponseEntity.noContent().build();
    }
}
