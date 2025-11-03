package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Transacao;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public Transacao create(Transacao transacao) {
        // Validação para id digitado
        if (transacao.getIdTransacao() != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.transacao.badrequest",
                    "O parâmento idTransacao não deve constar na requisição");
        }

        // Validação para usuario inexistente
//        if (transacao.getNomeTransacao() == null) {
//            throw new ApiException(HttpStatus.BAD_REQUEST,
//                    "unichristus.service.transacao.badrequest",
//                    "O parâmento nomeTransacao deve constar na requisição");
//        }

        // Validação para livro inexistente
//        if (transacao.getNomeTransacao().isBlank()) {
//            throw new ApiException(HttpStatus.BAD_REQUEST,
//                    "unichristus.service.transacao.badrequest",
//                    "O parâmetro nomeTransacao é obrigatório e não pode ser deixado em branco");
//        }

        // validação para tipo de transação
//        var novaTransacao = transacao.getNomeTransacao();
//        if (repository.findByNomeTransacaoIgnoreCase(novaTransacao) != null) {
//            throw new ApiException(HttpStatus.BAD_REQUEST,
//                    "unichristus.service.transacao.badrequest",
//                    "O parâmetro nomeTransacao deve ser único e já definido no banco de dados / nometransacao: " + novaTransacao);
//        }
        return repository.save(transacao);
    }

    public List<Transacao> findAll() {
        return repository.findAll();
    }

    public Transacao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.transacao.notfound",
                        "A transacao com o id pesquisado não foi localizada"));
    }

    public Transacao update(Transacao transacao) {
        // Validação para id digitado
        if (transacao.getIdTransacao() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.transacao.badrequest",
                    "O parâmento idTransacao deve constar na requisição");
        }

        // Validação para usuario inexistente

        // Validação para livro inexistente

        // validação para tipo de transação

        return repository.save(transacao);
    }

    public void deleteById(Long id) {
        Transacao transacao = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND,
                        "unichristus.service.transacao.notfound",
                        "A transacao com o id pesquisado não foi localizada"));
        repository.deleteById(id);
    }
}
