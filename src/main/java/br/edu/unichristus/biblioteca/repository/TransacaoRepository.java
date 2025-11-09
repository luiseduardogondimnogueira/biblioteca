package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.dto.TransacaoResumoPorUsuario;
import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import br.edu.unichristus.biblioteca.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // navegação via transacao -> livro -> autor -> idAutor
    boolean existsByLivro_Autor_IdAutor(Long idAutor);

    // navegação via transacao -> livro -> idLivro
    boolean existsByLivro_IdLivroAndTipo(Long idLivro, TipoTransacao tipo);

    // navegação via transacao -> usuario -> idUsuario
    boolean existsByUsuario_IdUsuario(Long idUsuario);

    // histórico de transações do usuário (filtrar por tipo opcionalmente)
    List<Transacao> findByUsuario_idUsuarioOrderByDataHoraDesc(Long id);

    List<Transacao> findByUsuario_idUsuarioAndTipoOrderByDataHoraDesc(Long id, TipoTransacao tipo);

    // contagem rápida: quantos acessos/downloads teve um livro
    // navegação via transacao -> livro -> idLivro + Tipo
    long countByLivro_IdLivroAndTipo(Long id, TipoTransacao tipo);

    // relatório de totais de acesso e downloads por usuario
    @Query("""
                SELECT new br.edu.unichristus.biblioteca.domain.dto.TransacaoResumoPorUsuario(
                    t.usuario.idUsuario,
                    t.usuario.nomeUsuario,
                    SUM(CASE WHEN t.tipo = br.edu.unichristus.biblioteca.domain.model.TipoTransacao.DOWNLOAD THEN 1 ELSE 0 END),
                    SUM(CASE WHEN t.tipo = br.edu.unichristus.biblioteca.domain.model.TipoTransacao.ACESSO THEN 1 ELSE 0 END),
                    COALESCE(SUM(t.valor), 0)
                )
                FROM Transacao t
                GROUP BY t.usuario.idUsuario, t.usuario.nomeUsuario
                ORDER BY t.usuario.idUsuario
            """)
    List<TransacaoResumoPorUsuario> ResumoPorUsuario();

}
