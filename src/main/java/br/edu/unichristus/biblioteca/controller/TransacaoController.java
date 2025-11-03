package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.model.Transacao;
import br.edu.unichristus.biblioteca.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    private Transacao create(@RequestBody Transacao transacao) {
        return service.create(transacao);
    }

    @GetMapping("/all")
    public List<Transacao> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Transacao findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Transacao update(@RequestBody Transacao transacao) {
        return service.update(transacao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

}
