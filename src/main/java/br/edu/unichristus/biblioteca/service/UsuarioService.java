package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Usuario;
import br.edu.unichristus.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository repository;

    public Usuario create(Usuario usuario) {
        var courseSaved = repository.save(usuario);
        return courseSaved;
    }

    public List<Usuario> getAll() {
        return repository.findAll();
    }

}
