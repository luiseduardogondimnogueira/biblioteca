package br.edu.unichristus.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(unique = true)
    private String nomeLivro;

    private Integer anoPublicacao;

    @Column(length = 1000)
    private String urlVisualizacao;

    @Column(length = 1000)
    private String urlDownload;

    private BigDecimal preco;

    // Relacionamento Many-to-One com Autor: Vários Livros para 1 Autor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    private Autor autor;

    // Relacionamento Many-to-One com Categoria: Vários Livros para 1 Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

}
