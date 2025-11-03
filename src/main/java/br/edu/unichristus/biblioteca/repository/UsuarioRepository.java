package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNomeUsuarioIgnoreCase(String nomeUsuario);
}
