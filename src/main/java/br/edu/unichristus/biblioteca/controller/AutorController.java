package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping
    private Autor create(@RequestBody Autor autor) {
        return service.create(autor);
    }

    @GetMapping("/all")
    public List<Autor> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Autor findById(@PathVariable(name = "idAutor") Long id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public List<Autor> findByNome(@RequestParam(name = "nomeAutor") String nome) {
        return service.findByName(nome);
    }

    @PutMapping
    public Autor update(@RequestBody Autor autor) {
        return service.update(autor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "idAutor") Long id) {
        service.deleteById(id);
    }

}
