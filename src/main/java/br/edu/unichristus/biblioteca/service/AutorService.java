package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor create(Autor autor) {
        return repository.save(autor);
    }

    public List<Autor> getAll() {
        return repository.findAll();
    }

}
