package br.edu.unichristus.biblioteca.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LivroResponse {

    private Long idLivro;

    private String nomeLivro;

    private Integer anoPublicacao;

    private String urlVisualizacao;

    private String urlDownload;

    private BigDecimal preco;

    private String nomeAutor;

    private String nomeCategoria;
}
