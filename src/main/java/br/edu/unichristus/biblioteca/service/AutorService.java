package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor create(Autor autor) {
        // Validação para nome do Autor em branco ou nulo
        if (autor.getNomeAutor().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do autor é obrigatório");
        }
        // validação para nome de usuario maior que 100 caracteres
        if (autor.getNomeAutor().length() > 100) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do autor não pode exceder 100 caracteres");
        }
        return repository.save(autor);
    }

    public List<Autor> findAll() {
        return repository.findAll();
    }

    public Autor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.user.badrequest",
                        "Autor não localizado com id: " + id));
    }

    public List<Autor> findByName(String nome) {
        List<Autor> autores = repository.findByNomeAutorContainingIgnoreCase(nome);
        if (autores.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "unichristus.service.user.badrequest",
                    "Nenhum autor localizado com o nome: " + nome);
        }
        return autores;
    }

    public Autor update(Autor autor) {
        // Validação para nome do Autor em branco ou nulo
        if (autor.getNomeAutor().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do autor é obrigatório");
        }
        // validação para nome de usuario maior que 100 caracteres
        if (autor.getNomeAutor().length() > 100) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do autor não pode exceder 100 caracteres");
        }
        return repository.save(autor);
    }

    public void deleteById(Long id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND,
                        "unichristus.service.autor.notfound",
                        "Autor não localizado com id: " + id));
        repository.deleteById(id);
    }

}