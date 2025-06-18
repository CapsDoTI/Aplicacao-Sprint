package com.ltpo.aplicacao.controllers;

import com.ltpo.aplicacao.models.Usuario;
import com.ltpo.aplicacao.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/{usuarioId}/cursos")
    public ResponseEntity<Void> adicionarCurso(@PathVariable Long usuarioId, @RequestBody String nomeCurso) {
        usuarioService.adicionarCursoAoUsuario(usuarioId, nomeCurso);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}