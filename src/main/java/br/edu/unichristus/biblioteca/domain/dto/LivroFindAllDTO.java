package br.edu.unichristus.biblioteca.domain.dto;

import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.domain.model.Categoria;
import lombok.Data;

import java.math.BigDecimal;

// Representação de Livro, ajustada com AutorSimplesDTO e CategoriaSimplesDTO para evitar loop infinito

@Data
public class LivroFindAllDTO {

    private Long idLivro;

    private String nomeLivro;

    private Integer anoPublicacao;

    private String urlVisualizacao;

    private String urlDownload;

    private BigDecimal preco;

    private AutorSimplesDTO autor;

    private CategoriaSimplesDTO categoria;

}
