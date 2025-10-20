package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}