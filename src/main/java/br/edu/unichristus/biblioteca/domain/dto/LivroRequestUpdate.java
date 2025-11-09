package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LivroRequestUpdate {

    @NotNull(message = "O id do livro é obrigatório")
    private Long idLivro;

    @NotBlank(message = "O nome do livro é obrigatório")
    private String nomeLivro;

    private Integer anoPublicacao;

    @Size(max = 1000, message = "A url de visualização deve ter no máximo 1000 caracteres")
    private String urlVisualizacao;

    @Size(max = 1000, message = "A url de download deve ter no máximo 1000 caracteres")
    private String urlDownload;

    private BigDecimal preco;

    @NotNull(message = "O id do autor é obrigatório")
    private Long idAutor;

    @NotNull(message = "O id da categoria é obrigatório")
    private Long idCategoria;

}
