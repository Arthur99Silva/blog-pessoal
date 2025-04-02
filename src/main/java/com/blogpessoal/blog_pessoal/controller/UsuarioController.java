package com.blogpessoal.blog_pessoal.controller;

import com.blogpessoal.blog_pessoal.model.Usuario;
import com.blogpessoal.blog_pessoal.services.UsuarioService; // Certifique-se de que o pacote de serviços está correto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")  // Definindo o caminho base para as requisições
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // Injetando o serviço de usuário

    // Endpoint para criar um novo usuário
    @PostMapping
    public Usuario salvarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    // Endpoint para listar todos os usuários
    @GetMapping
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioService.buscarTodosUsuarios();
    }

    // Endpoint para buscar um usuário pelo ID
    @GetMapping("/{id}")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    // Endpoint para buscar usuários por nome
    @GetMapping("/nome/{nome}")
    public List<Usuario> buscarUsuariosPorNome(@PathVariable String nome) {
        return usuarioService.buscarUsuariosPorNome(nome);
    }
}
