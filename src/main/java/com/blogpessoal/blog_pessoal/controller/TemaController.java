package com.blogpessoal.blog_pessoal.controller;

import java.util.List;
import java.util.Optional; // Certifique-se de que o pacote de serviços está correto

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.blog_pessoal.model.Tema;
import com.blogpessoal.blog_pessoal.services.TemaService;

@RestController
@RequestMapping("/temas")  // Caminho base para os temas
public class TemaController {

    @Autowired
    private TemaService temaService;

    // Endpoint para criar um novo tema
    @PostMapping
    public Tema salvarTema(@RequestBody Tema tema) {
        return temaService.salvarTema(tema);
    }

    // Endpoint para listar todos os temas
    @GetMapping
    public List<Tema> buscarTodosTemas() {
        return temaService.buscarTodosTemas();
    }

    // Endpoint para buscar um tema por ID
    @GetMapping("/{id}")
    public Optional<Tema> buscarTemaPorId(@PathVariable Long id) {
        return temaService.buscarTemaPorId(id);
    }
}
