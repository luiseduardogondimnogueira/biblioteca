package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Livro;
import br.edu.unichristus.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    public LivroRepository repository;

    public Livro create(Livro livro) {
        var courseSaved = repository.save(livro);
        return courseSaved;
    }

    public List<Livro> getAll() {
        return repository.findAll();
    }

}
