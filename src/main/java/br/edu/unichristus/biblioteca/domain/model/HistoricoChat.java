package br.edu.unichristus.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long session_id;

    private String message_id;

    private String message;

    private LocalDateTime horario;

    // Relacionamento Many-to-One com Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // A anotação @ManyToOne indica que muitos registros de HistoricoChat se referem a um único Usuário.
    // fetch = FetchType.LAZY garante que o objeto Usuario só será carregado do banco de dados quando for explicitamente
    // acessado, otimizando o desempenho.
    // @JoinColumn(name = "usuario_id") define a coluna de chave estrangeira que ligará esta tabela à tabela Usuario.
    // O nome usuario_id é o nome da chave primária na classe Usuario.

}