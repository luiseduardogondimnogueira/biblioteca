package br.edu.unichristus.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao", indexes = {
        @Index(columnList = "idUsuario"),
        @Index(columnList = "idLivro"),
        @Index(columnList = "dataHora"),
        @Index(columnList = "tipo")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLivro", nullable = false)
    private Livro livro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoTransacao tipo;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 1000)
    private String urlVisualizacao;

    @Column(length = 1000)
    private String urlDownload;

    private BigDecimal valor;
}
