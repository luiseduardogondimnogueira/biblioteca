package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.LivroResponse;
import br.edu.unichristus.biblioteca.domain.dto.LivroFindAllDTO;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.domain.model.Livro;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import br.edu.unichristus.biblioteca.repository.LivroRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    public LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro create(Livro livro) {
        // Validação para id digitado
        if (livro.getIdLivro() != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parâmento idLivro não deve constar na requisição");
        }

        // Validação para nome da livro ausente
        if (livro.getNomeLivro() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parâmento nomeLivro deve constar na requisição");
        }

        // Validação para nome da livro em branco
        if (livro.getNomeLivro().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parâmetro nomeLivro é obrigatório e não pode ser deixado em branco");
        }

        // validação para nome da livro repetida
        var novoLivro = livro.getNomeLivro();
        if (repository.findByNomeLivroIgnoreCase(novoLivro) != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parâmetro nomeLivro deve ser único e já definido no banco de dados / nomelivro: " + novoLivro);
        }
        return repository.save(livro);
    }

    public List<LivroFindAllDTO> findAll() {
        List<Livro> livros = repository.findAll();
        return MapperUtil.parseListObjects(livros, LivroFindAllDTO.class);
    }

    public LivroResponse findById(Long id) {
        Livro livroPesquisado = repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.livro.notfound",
                        "A livro com o id pesquisado não foi localizado"));
        return MapperUtil.parseObject(livroPesquisado, LivroResponse.class);
    }

    public List<LivroResponse> listarLivrosPorAutor(long idAutor) {
        Autor autorPesquisado = autorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + idAutor + " não foi localizado."));
        String nomeAutor = autorPesquisado.getNomeAutor();

        List<Livro> livros = repository.findByAutorIdAutor(idAutor);
        if (livros.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Nenhum livro cadastrado para autor '" + nomeAutor + "' foi localizado.");
        }

        return MapperUtil.parseListObjects(livros, LivroResponse.class);
    }

    public Livro update(Livro livro) {
        // Validação para id digitado
        if (livro.getIdLivro() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parâmento idLivro deve constar na requisição");
        }

        // Validação para nome da livro ausente
        if (livro.getNomeLivro() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parâmento nomeLivro deve constar na requisição");
        }

        // Validação para nome da livro em branco
        if (livro.getNomeLivro().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O parêmetro nomeLivro é obrigatório e não pode ser deixado em branco");
        }

        return repository.save(livro);
    }

    public void deleteById(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND,
                        "unichristus.service.livro.notfound",
                        "A livro com o id pesquisado não foi localizada"));
        repository.deleteById(id);
    }
}
