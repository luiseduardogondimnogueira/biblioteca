package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequest;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaResponse;
import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    private CategoriaResponse create(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        return service.create(categoriaRequest);
    }

    @GetMapping("/all")
    public List<CategoriaResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoriaResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public CategoriaResponse update(@Valid @RequestBody CategoriaRequestUpdate categoriaRequestUpdate) {
        return service.update(categoriaRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long idCategoria) {
        service.deleteById(idCategoria);
    }

}
