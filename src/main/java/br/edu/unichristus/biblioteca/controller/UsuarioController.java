package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.UsuarioRequest;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioResponse;
import br.edu.unichristus.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public UsuarioResponse create(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return service.create(usuarioRequest);
    }

    @GetMapping("/all")
    public List<UsuarioResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public UsuarioResponse update(@Valid @RequestBody UsuarioRequestUpdate usuarioRequestUpdate) {
        return service.update(usuarioRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

}