package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LivroRequest {

    @NotBlank
    private String nomeLivro;

    private Integer anoPublicacao;

    @Size(max = 1000, message = "A urlVisualizacao deve ter no máximo 1000 caracteres")
    private String urlVisualizacao;

    @Size(max = 1000, message = "A urlDownload deve ter no máximo 1000 caracteres")
    private String urlDownload;

    private BigDecimal preco;

    @NotNull
    private Long idAutor;

    @NotNull
    private Long idCategoria;
}
