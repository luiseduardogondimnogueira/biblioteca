package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.repository.HistoricoChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoChatService {

    @Autowired
    private HistoricoChatRepository repository;

    public HistoricoChat create(HistoricoChat historicoChat) {
        return repository.save(historicoChat);
    }

    public List<HistoricoChat> findAll() {
        return repository.findAll();
    }

    public HistoricoChat findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.historicochat.notfound",
                        "O historicoChat com o id pesquisado não foi localizado"));
    }

    public HistoricoChat update(HistoricoChat historicoChat) {
        return repository.save(historicoChat);
    }

    public void deleteById(Long id) {
        HistoricoChat historicoChat = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND,
                        "unichristus.service.historicochat.notfound",
                        "O historicoChat com o id pesquisado não foi localizado"));
        repository.deleteById(id);
    }
}
