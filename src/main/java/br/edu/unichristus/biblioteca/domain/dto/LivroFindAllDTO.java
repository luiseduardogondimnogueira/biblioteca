package br.edu.unichristus.biblioteca.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

// Representação de Livro, ajustada com AutorSimplesDTO e CategoriaSimplesDTO para
// evitar loop infinito, mas permitindo que a estrutura fique aninhada

@Data
public class LivroFindAllDTO {

    private Long idLivro;

    private String nomeLivro;

    private Integer anoPublicacao;

    private String urlVisualizacao;

    private String urlDownload;

    private BigDecimal preco;

//    private String nomeAutor;
    private AutorSimplesDTO autor;

//    private String nomeCategoria;
    private CategoriaSimplesDTO categoria;
}
