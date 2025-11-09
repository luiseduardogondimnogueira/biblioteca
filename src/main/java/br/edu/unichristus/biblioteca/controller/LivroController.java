package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.LivroRequest;
import br.edu.unichristus.biblioteca.domain.dto.LivroRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.LivroResponse;
import br.edu.unichristus.biblioteca.domain.dto.LivroResponseFindAll;
import br.edu.unichristus.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public LivroResponse create(@Valid @RequestBody LivroRequest livroRequest) {
        return service.create(livroRequest);
    }

    @GetMapping("/all")
    public List<LivroResponseFindAll> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LivroResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public LivroResponse update(@Valid @RequestBody LivroRequestUpdate livroRequestUpdate) {
        return service.update(livroRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }
}
