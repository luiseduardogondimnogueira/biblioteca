package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // navegação via livro -> autor -> idAutor
    boolean existsByAutor_IdAutor(Long idAutor);

    // navegação via livro -> categoria -> idCategoria
    boolean existsByCategoria_IdCategoria(Long idCategoria);

    Optional<Livro> findFirstByNomeLivroIgnoreCase(String nomeLivro);

    List<Livro> findByNomeLivroIgnoreCase(String nomeLivro);

    List<Livro> findByAutorIdAutor(long idAutor);
}