package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.LivroResponse;
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
    public LivroResponse findById(@PathVariable(name = "id") Long idLivro) {
        return service.findById(idLivro);
    }

    @PutMapping
    public Livro update(@RequestBody Livro livro) {
        return service.update(livro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long idLivro) {
        service.deleteById(idLivro);
    }
}
