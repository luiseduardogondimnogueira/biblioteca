package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.model.Usuario;
import br.edu.unichristus.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    private Usuario create(@RequestBody Usuario usuario) {
        return service.create(usuario);
    }

    @GetMapping("/all")
    public List<Usuario> getAll() {
        return service.getAll();
    }
}
