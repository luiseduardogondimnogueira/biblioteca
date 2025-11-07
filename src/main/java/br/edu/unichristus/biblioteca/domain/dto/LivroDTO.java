package br.edu.unichristus.biblioteca.domain.dto;

import br.edu.unichristus.biblioteca.domain.model.Categoria;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LivroDTO {

    private Long idLivro;

    private String nomeLivro;

    private Integer anoPublicacao;

    private String urlVisualizacao;

    private String urlDownload;

    private BigDecimal preco;

    private String nomeAutor;

    private String nomeCategoria;

}
