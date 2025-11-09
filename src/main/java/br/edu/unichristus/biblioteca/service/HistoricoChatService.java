package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatRequest;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatResponse;
import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.HistoricoChatRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoChatService {

    @Autowired
    private HistoricoChatRepository repository;

    public HistoricoChatResponse create(HistoricoChatRequest historicoChatRequest) {
        HistoricoChat novoHistoricoChat = MapperUtil.parseObject(historicoChatRequest, HistoricoChat.class);
        repository.save(novoHistoricoChat);
        return MapperUtil.parseObject(novoHistoricoChat, HistoricoChatResponse.class);
    }

    public List<HistoricoChatResponse> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), HistoricoChatResponse.class);
    }

    public HistoricoChatResponse findById(Long id) {
        HistoricoChat historicoChatPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O histórico de chat com o id " + id + " não foi localizado."));
        return MapperUtil.parseObject(historicoChatPesquisado,HistoricoChatResponse.class);
    }

    public HistoricoChatResponse update(HistoricoChatRequestUpdate historicoChatRequestUpdate) {
        // BUSCAR o objeto
        Long id = historicoChatRequestUpdate.getIdSession();
        HistoricoChat historicoChatAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A historicoChat com o id " + id + " não foi localizado para atualização."));

        // VALIDAR (Regras de negócio)

        // MODIFICAR/ATUALIZAR (campos do DTO)
        historicoChatAtualizar.setIdMessage(historicoChatRequestUpdate.getIdMessage());
        historicoChatAtualizar.setMessage(historicoChatRequestUpdate.getMessage());
        historicoChatAtualizar.setHorario(historicoChatRequestUpdate.getHorario());

        // SALVAR o objeto atualizado
        repository.save(historicoChatAtualizar);

        return MapperUtil.parseObject(historicoChatAtualizar, HistoricoChatResponse.class);
    }

    public void deleteById(Long id) {
        HistoricoChat historicoChatPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O histórico de chat com o id " + id + " não foi localizado."));
        repository.deleteById(id);
    }

}
