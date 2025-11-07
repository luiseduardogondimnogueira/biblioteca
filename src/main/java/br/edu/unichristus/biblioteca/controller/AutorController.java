package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorDTO;
import br.edu.unichristus.biblioteca.domain.dto.LivroDTO;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.service.AutorService;
import br.edu.unichristus.biblioteca.service.LivroService;
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
    private Autor create(@RequestBody Autor autor) {
        return service.create(autor);
    }

    @GetMapping("/all")
    public List<AutorDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
//    public Autor findById(@PathVariable(name = "idAutor") Long id) {
    public AutorDTO findById(@PathVariable(name = "id") Long idAutor) {
            return service.findById(idAutor);
    }

    @GetMapping("/{idAutor}/livros")
    public List<LivroDTO> listarLivrosPorAutor(@PathVariable(name = "idAutor") Long id) {
        List<LivroDTO> livrosDoAutor = livroService.listarLivrosPorAutor(id);
        return livrosDoAutor;
    }

    @GetMapping("/search")
    public List<AutorDTO> findByNome(@RequestParam(name = "nomeAutor") String nome) {
        return service.findByName(nome);
    }

    @PutMapping
    public Autor update(@RequestBody Autor autor) {
        return service.update(autor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long idAutor) {
        service.deleteById(idAutor);
    }

}
