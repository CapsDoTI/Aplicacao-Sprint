package com.ltpo.aplicacao.services;

import com.ltpo.aplicacao.models.Cursos;
import com.ltpo.aplicacao.models.Usuario;
import com.ltpo.aplicacao.repositories.CursosRepository;
import com.ltpo.aplicacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursosRepository cursosRepository;

    public void adicionarCursoAoUsuario(Long usuarioId, String nomeCurso) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Cursos curso = new Cursos();
        curso.setNome(nomeCurso);
        curso.setUsuario(usuario);

        usuario.getCursos().add(curso);

        usuarioRepository.save(usuario);
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        // Criptografar senha em aplicação real
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Outros métodos como buscar, atualizar, excluir
}