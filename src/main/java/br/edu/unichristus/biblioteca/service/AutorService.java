package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.AutorDTO;
import br.edu.unichristus.biblioteca.domain.dto.LivroDTO;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor create(Autor autor) {
        // Validação para nome do Autor em branco
        if (autor.getNomeAutor().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do autor é obrigatório");
        }
        // validação para nome do Autor maior que 100 caracteres
        if (autor.getNomeAutor().length() > 100) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do autor não pode exceder 100 caracteres");
        }
        return repository.save(autor);
    }

    public List<AutorDTO> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), AutorDTO.class);

    }

    public AutorDTO findById(Long id) {
        Autor autorPesquisado = repository.findById(id).
                orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.user.badrequest",
                        "Autor não localizado com id: " + id));
        return MapperUtil.parseObject(autorPesquisado, AutorDTO.class);

    }

    public List<AutorDTO> findByName(String nome) {
        List<Autor> autores = repository.findByNomeAutorContainingIgnoreCase(nome);
        if (autores.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "unichristus.service.user.badrequest",
                    "Nenhum autor localizado com o nome: " + nome);
        }
        return MapperUtil.parseListObjects(autores, AutorDTO.class);
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