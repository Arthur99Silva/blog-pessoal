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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/postagens")
@Tag(name = "Postagens", description = "Endpoints para gerenciar postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Operation(summary = "Criar nova postagem")
    @ApiResponse(responseCode = "200", description = "Postagem criada com sucesso")
    @PostMapping
    public Postagem salvarPostagem(@RequestBody Postagem postagem) {
        return postagemService.salvarPostagem(postagem);
    }

    @Operation(summary = "Listar todas as postagens")
    @ApiResponse(responseCode = "200", description = "Lista de postagens obtida com sucesso")
    @GetMapping
    public List<Postagem> buscarTodasPostagens() {
        return postagemService.buscarTodasPostagens();
    }

    @Operation(summary = "Buscar postagens por título")
    @ApiResponse(responseCode = "200", description = "Postagens com título específico obtidas com sucesso")
    @GetMapping("/titulo/{titulo}")
    public List<Postagem> buscarPostagensPorTitulo(@PathVariable String titulo) {
        return postagemService.buscarPostagensPorTitulo(titulo);
    }

    @Operation(summary = "Buscar postagens por usuário")
    @ApiResponse(responseCode = "200", description = "Postagens do usuário obtidas com sucesso")
    @GetMapping("/usuario/{usuarioId}")
    public List<Postagem> buscarPostagensPorUsuario(@PathVariable Long usuarioId) {
        return postagemService.buscarPostagensPorUsuario(usuarioId);
    }

    @Operation(summary = "Buscar postagem por ID")
    @ApiResponse(responseCode = "200", description = "Postagem encontrada com sucesso")
    @GetMapping("/{id}")
    public Optional<Postagem> buscarPostagemPorId(@PathVariable Long id) {
        return postagemService.buscarPostagemPorId(id);
    }

    @Operation(summary = "Excluir postagem por ID")
    @ApiResponse(responseCode = "204", description = "Postagem excluída com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPostagem(@PathVariable Long id) {
        postagemService.excluirPostagem(id);
        return ResponseEntity.noContent().build();
    }
}
