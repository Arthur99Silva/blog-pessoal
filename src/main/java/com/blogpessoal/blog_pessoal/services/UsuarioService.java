package com.blogpessoal.blog_pessoal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogpessoal.blog_pessoal.model.Usuario;
import com.blogpessoal.blog_pessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Método para salvar um novo usuário
    public Usuario salvarUsuario(Usuario usuario) {
        String senhaCodificada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCodificada);
        return usuarioRepository.save(usuario);
    }

    // Método para buscar todos os usuários
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para buscar um usuário por ID
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para buscar usuários por nome
    public List<Usuario> buscarUsuariosPorNome(String nome) {
        return usuarioRepository.findByNomeContaining(nome);
    }

    // Método para buscar usuários por email
    public List<Usuario> buscarUsuariosPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
