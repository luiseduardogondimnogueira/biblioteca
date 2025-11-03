package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import br.edu.unichristus.biblioteca.service.HistoricoChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/historicochat")
public class HistoricoChatController {

    @Autowired
    private HistoricoChatService service;

    @PostMapping
    private HistoricoChat create(@RequestBody HistoricoChat historicoChat) {
        return service.create(historicoChat);
    }

    @GetMapping("/all")
    public List<HistoricoChat> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public HistoricoChat findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public HistoricoChat update(@RequestBody HistoricoChat historicoChat) {
        return service.update(historicoChat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }
}
