package br.edu.unichristus.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    private String titulo;

    private Integer anoPublicacao;

    @Column(length = 1000)
    private String urlVisualizacao;

    @Column(length = 1000)
    private String urlDownload;

    private BigDecimal preco;

    // Relacionamento Many-to-One com Autor: Vários livros para 1 autor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    private Autor autor;

    // Relacionamento Many-to-One com Categoria: Vários livros para 1 categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
}
