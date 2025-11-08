package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorRequest;
import br.edu.unichristus.biblioteca.domain.dto.AutorRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.dto.LivroResponse;
import br.edu.unichristus.biblioteca.service.AutorService;
import br.edu.unichristus.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @Autowired
    private LivroService livroService;

    @PostMapping
    public AutorResponse create(@Valid @RequestBody AutorRequest autorRequest) {
        return service.create(autorRequest);
    }

    @GetMapping("/all")
    public List<AutorResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AutorResponse findById(@PathVariable(name = "id") Long id) {
            return service.findById(id);
    }

    @GetMapping("/{idAutor}/livros")
    public List<LivroResponse> listarLivrosPorAutor(@PathVariable(name = "idAutor") Long id) {
        List<LivroResponse> livrosDoAutor = livroService.listarLivrosPorAutor(id);
        return livrosDoAutor;
    }

    @GetMapping("/search")
    public List<AutorResponse> findByNome(@RequestParam(name = "nomeAutor") String nome) {
        return service.findByName(nome);
    }

    @PutMapping
    public AutorResponse update(@Valid @RequestBody AutorRequestUpdate autorRequestUpdate) {
        return service.update(autorRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

}
