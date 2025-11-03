package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoChatRepository extends JpaRepository<HistoricoChat, Long> {
}
