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
    public List<Usuario> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Usuario update(@RequestBody Usuario usuario) {
        return service.update(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

}