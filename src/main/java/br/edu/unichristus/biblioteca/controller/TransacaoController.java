package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.TransacaoDTO;
import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
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
    public Transacao create(@RequestBody Transacao transacao) {
        return service.create(transacao);
    }

    @GetMapping("/all")
    public List<TransacaoDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TransacaoDTO findById(@PathVariable(name = "id") Long idTransacao) {
        return service.findById(idTransacao);
    }

    @PutMapping
    public Transacao update(@RequestBody Transacao transacao) {
        return service.update(transacao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long idTransacao) {
        service.deleteById(idTransacao);
    }

    @GetMapping("/usuario/{id}")
    public List<TransacaoDTO> findByUsuario(@PathVariable(name = "id") Long idUsuario,
                                            @RequestParam(required = false) TipoTransacao tipo) {
        return service.findTransacaoByUsuario(idUsuario, tipo);
    }
}
