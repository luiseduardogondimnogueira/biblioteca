package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatRequest;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatResponse;
import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import br.edu.unichristus.biblioteca.service.HistoricoChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/historicochat")
public class HistoricoChatController {

    @Autowired
    private HistoricoChatService service;

    @PostMapping
    public HistoricoChatResponse create(@Valid @RequestBody HistoricoChatRequest historicoChatRequest) {
        return service.create(historicoChatRequest);
    }

    @GetMapping("/all")
    public List<HistoricoChatResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public HistoricoChatResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public HistoricoChatResponse update(@Valid @RequestBody HistoricoChatRequestUpdate historicoChatRequestUpdate) {
        return service.update(historicoChatRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }
}
