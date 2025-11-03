package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
