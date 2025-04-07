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

import com.blogpessoal.blog_pessoal.model.Tema;
import com.blogpessoal.blog_pessoal.services.TemaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/temas")
@Tag(name = "Temas", description = "Endpoints para gerenciar temas das postagens")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @Operation(summary = "Criar novo tema")
    @ApiResponse(responseCode = "200", description = "Tema criado com sucesso")
    @PostMapping
    public Tema salvarTema(@RequestBody Tema tema) {
        return temaService.salvarTema(tema);
    }

    @Operation(summary = "Listar todos os temas")
    @ApiResponse(responseCode = "200", description = "Lista de temas obtida com sucesso")
    @GetMapping
    public List<Tema> buscarTodosTemas() {
        return temaService.buscarTodosTemas();
    }

    @Operation(summary = "Buscar tema por ID")
    @ApiResponse(responseCode = "200", description = "Tema encontrado com sucesso")
    @GetMapping("/{id}")
    public Optional<Tema> buscarTemaPorId(@PathVariable Long id) {
        return temaService.buscarTemaPorId(id);
    }

    @Operation(summary = "Excluir tema por ID")
    @ApiResponse(responseCode = "204", description = "Tema excluído com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTema(@PathVariable Long id) {
        temaService.excluirTema(id);
        return ResponseEntity.noContent().build();
    }
}
