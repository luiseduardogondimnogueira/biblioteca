package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.AutorRequest;
import br.edu.unichristus.biblioteca.domain.dto.AutorRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.exception.ResourceConflictException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import br.edu.unichristus.biblioteca.repository.LivroRepository;
import br.edu.unichristus.biblioteca.repository.TransacaoRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public AutorResponse create(AutorRequest autorRequest) {
        Autor novoAutor = MapperUtil.parseObject(autorRequest, Autor.class);
        repository.save(novoAutor);

        return MapperUtil.parseObject(novoAutor, AutorResponse.class);
    }

    public List<AutorResponse> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), AutorResponse.class);

    }

    public AutorResponse findById(Long id) {
        Autor autorPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + id + " não foi localizado."));
        return MapperUtil.parseObject(autorPesquisado, AutorResponse.class);

    }

    public AutorResponse update(AutorRequestUpdate autorRequestUpdate) {
        // BUSCAR o objeto
        Long id = autorRequestUpdate.getIdAutor();
        Autor autorAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + id + " não foi localizado para atualização."));

        // VALIDAR (Regras de negócio)

        // MODIFICAR/ATUALIZAR (campos do DTO)
        autorAtualizar.setNomeAutor(autorRequestUpdate.getNomeAutor());
        autorAtualizar.setDataNascimento(autorRequestUpdate.getDataNascimento());
        autorAtualizar.setNacionalidade(autorRequestUpdate.getNacionalidade());
        autorAtualizar.setBiografia(autorRequestUpdate.getBiografia());

        // SALVAR o objeto atualizado
        repository.save(autorAtualizar);

        return MapperUtil.parseObject(autorAtualizar, AutorResponse.class);
    }

    public void deleteById(Long id) {
        Autor autorPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + id + " não foi localizado."));

        // VALIDAR RELACIONAMENTOS — se existir qualquer livro ou transação, não delete!
        boolean temLivros = livroRepository.existsByAutor_IdAutor(id);
        boolean temTransacoes = transacaoRepository.existsByLivro_Autor_IdAutor(id);

        if (temLivros || temTransacoes) {
            String motivo = (temLivros ? "Existem livros cadastrados para este autor" : "") +
                    (temLivros && temTransacoes ? ". " : "") +
                    (temTransacoes ? "Existem transações relacionadas a este autor" : "");
            throw new ResourceConflictException(
                    "Não é possível excluir o autor de id " + id + ". " + motivo + ".");
        }

        // REMOVER o autor
        repository.deleteById(id);
    }


    // ----- FEATURES ----- //


    public List<AutorResponse> findByName(String nome) {
        List<Autor> autores = repository.findByNomeAutorContainingIgnoreCase(nome);
        if (autores.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Nenhum autor cujo nome contenha '" + nome + "' foi localizado.");
        }
        return MapperUtil.parseListObjects(autores, AutorResponse.class);
    }

}