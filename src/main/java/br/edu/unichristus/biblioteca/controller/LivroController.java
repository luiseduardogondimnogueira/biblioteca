package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.LivroFindAllDTO;
import br.edu.unichristus.biblioteca.domain.model.Livro;
import br.edu.unichristus.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    private Livro create(@RequestBody Livro livro) {
        return service.create(livro);
    }

    @GetMapping("/all")
    public List<LivroFindAllDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Livro findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Livro update(@RequestBody Livro livro) {
        return service.update(livro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }
}
