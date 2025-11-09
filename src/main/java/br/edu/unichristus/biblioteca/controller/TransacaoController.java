package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.TransacaoRequest;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoResponse;
import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import br.edu.unichristus.biblioteca.domain.model.Transacao;
import br.edu.unichristus.biblioteca.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    public TransacaoResponse create(@Valid @RequestBody TransacaoRequest transacaoRequest) {
        return service.create(transacaoRequest);
    }

    @GetMapping("/all")
    public List<TransacaoResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TransacaoResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    public TransacaoResponse update(@Valid @RequestBody TransacaoRequestUpdate transacaoRequestUpdate) {
        return service.update(transacaoRequestUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }


    // ----- FEATURES ----- //


    @GetMapping("/usuario/{id}")
    public List<TransacaoResponse> findByUsuario(
            @PathVariable(name = "id") Long idUsuario,
            @RequestParam(required = false) TipoTransacao tipo) {
        return service.findTransacaoByUsuario(idUsuario, tipo);
    }

}
