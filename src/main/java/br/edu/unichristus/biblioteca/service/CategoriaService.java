package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria create(Categoria categoria) {
        return repository.save(categoria);
    }

    public List<Categoria> getAll() {
        return repository.findAll();
    }

}
