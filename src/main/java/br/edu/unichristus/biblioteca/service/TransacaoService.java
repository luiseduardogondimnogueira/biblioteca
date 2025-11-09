package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.TransacaoRequest;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoResponse;
import br.edu.unichristus.biblioteca.domain.model.*;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.LivroRepository;
import br.edu.unichristus.biblioteca.repository.TransacaoRepository;
import br.edu.unichristus.biblioteca.repository.UsuarioRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public TransacaoResponse create(TransacaoRequest transacaoRequest) {
        // VALIDAR (Usuario inexistente)
        long idUsuario = transacaoRequest.getIdUsuario();
        Usuario usuarioPesquisado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O usuario com o id " + idUsuario + " não foi localizado."));

        // VALIDAR (Livro inexistente)
        long idLivro = transacaoRequest.getIdLivro();
        Livro livroPesquisado = livroRepository.findById(idLivro)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O livro com o id " + idLivro + " não foi localizado."));

        // MODIFICAR/ATUALIZAR (campos do DTO)
        Transacao novaTransacao = new Transacao();
        novaTransacao.setUsuario(usuarioPesquisado);
        novaTransacao.setLivro(livroPesquisado);
        novaTransacao.setTipo(transacaoRequest.getTipo());
        novaTransacao.setDataHora(transacaoRequest.getDataHora());
        novaTransacao.setUrlVisualizacao(transacaoRequest.getUrlVisualizacao());
        novaTransacao.setUrlDownload(transacaoRequest.getUrlDownload());
        novaTransacao.setValor(transacaoRequest.getValor());

        // SALVAR o objeto atualizado
        repository.save(novaTransacao);

        return MapperUtil.parseObject(novaTransacao, TransacaoResponse.class);
    }

    public List<TransacaoResponse> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), TransacaoResponse.class);
    }

    public TransacaoResponse findById(Long id) {
        Transacao transacaoPesquisada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A transação com o id " + id + " não foi localizada."));
        return MapperUtil.parseObject(transacaoPesquisada, TransacaoResponse.class);
    }

    public TransacaoResponse update(TransacaoRequestUpdate transacaoRequestUpdate) {
        // BUSCAR o objeto
        Long id = transacaoRequestUpdate.getIdTransacao();
        Transacao transacaoAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A transacao com o id " + id + " não foi localizada para atualização."));

        // VALIDAR (Usuario inexistente)
        long idUsuario = transacaoRequestUpdate.getIdUsuario();
        Usuario usuarioPesquisado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O usuario com o id " + idUsuario + " não foi localizado."));

        // VALIDAR (Livro inexistente)
        long idLivro = transacaoRequestUpdate.getIdLivro();
        Livro livroPesquisado = livroRepository.findById(idLivro)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O livro com o id " + idLivro + " não foi localizado."));

        // MODIFICAR/ATUALIZAR (campos do DTO)
        transacaoAtualizar.setUsuario(usuarioPesquisado);
        transacaoAtualizar.setLivro(livroPesquisado);
        transacaoAtualizar.setTipo(transacaoRequestUpdate.getTipo());
        transacaoAtualizar.setDataHora(transacaoRequestUpdate.getDataHora());
        transacaoAtualizar.setUrlVisualizacao(transacaoRequestUpdate.getUrlVisualizacao());
        transacaoAtualizar.setUrlDownload(transacaoRequestUpdate.getUrlDownload());
        transacaoAtualizar.setValor(transacaoRequestUpdate.getValor());

        // SALVAR o objeto atualizado
        repository.save(transacaoAtualizar);

        return MapperUtil.parseObject(transacaoAtualizar, TransacaoResponse.class);
    }

    public void deleteById(Long id) {
        Transacao transacaoPesquisada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A transação com o id " + id + " não foi localizada."));
        repository.deleteById(id);
    }


    // ----- FEATURES ----- //


    public List<TransacaoResponse> findTransacaoByUsuario(Long id, TipoTransacao tipo) {

        // VALIDAR (Usuario inexistente)
        Usuario usuarioPesquisado = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O usuario com o id " + id + " não foi localizado."));

        List<Transacao> transacoesPesquisadas;

        if (tipo == null) {
            transacoesPesquisadas = repository.findByUsuario_idUsuarioOrderByDataHoraDesc(id);
            if (transacoesPesquisadas == null || transacoesPesquisadas.isEmpty()) {
                throw new ResourceNotFoundException(
                        "Nenhuma transação do usuario com o id " + id + " foi localizada.");
            }
        } else {
            transacoesPesquisadas = repository.findByUsuario_idUsuarioAndTipoOrderByDataHoraDesc(id, tipo);
            if (transacoesPesquisadas == null || transacoesPesquisadas.isEmpty()) {
                throw new ResourceNotFoundException(
                        "Nenhuma transação de " + tipo + " do usuario com o id " + id + "foi localizada.");
            }
        }

        return MapperUtil.parseListObjects(transacoesPesquisadas, TransacaoResponse.class);
    }

}