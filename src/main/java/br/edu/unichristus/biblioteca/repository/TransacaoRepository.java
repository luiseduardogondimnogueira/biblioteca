package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import br.edu.unichristus.biblioteca.domain.model.Transacao;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // histórico de transações do usuário
    List<Transacao> findByUsuario_idUsuarioOrderByDataHoraDesc(Long id);

    // transações de um livro (filtrar por tipo opcionalmente)
    List<Transacao> findByLivro_idLivroAndTipo(Long id, TipoTransacao tipo);

    // transações de um livro em um período
    List<Transacao> findByLivro_IdLivroAndDataHoraBetween(Long id, LocalDateTime inicio, LocalDateTime fim);

    // transações por tipo em um período (ex.: downloads no período)
    List<Transacao> findByTipoAndDataHoraBetween(TipoTransacao tipo, LocalDateTime inicio, LocalDateTime fim);

    // contagem rápida: quantos acessos/downloads teve um livro
    long countByLivro_IdLivroAndTipo(Long id, TipoTransacao tipo);

    // IA -> contar/transações agregadas por livro para ranking (retorna [Livro, Long])
    @Query("SELECT t.livro, COUNT(t) FROM Transacao t WHERE t.tipo = :tipo GROUP BY t.livro ORDER BY COUNT(t) DESC")
    java.util.List<Object[]> findTopLivrosByTipo(@Param("tipo") TipoTransacao tipo);

}
