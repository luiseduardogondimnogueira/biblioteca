package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import br.edu.unichristus.biblioteca.repository.HistoricoChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoChatService {

    @Autowired
    public HistoricoChatRepository repository;

    public HistoricoChat create(HistoricoChat historicoChat) {
        return repository.save(historicoChat);
    }

    public List<HistoricoChat> getAll() {
        return repository.findAll();
    }

}
