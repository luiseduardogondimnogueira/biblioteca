package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    private Categoria create(@RequestBody Categoria categoria) {
        return service.create(categoria);
    }

    @GetMapping("/all")
    public List<Categoria> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Categoria update(@RequestBody Categoria categoria) {
        return service.update(categoria);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

}
