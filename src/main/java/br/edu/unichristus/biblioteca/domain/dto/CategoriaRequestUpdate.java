package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaRequestUpdate {

    @NotNull (message = "O id da categoria é obrigatório")
    private Long idCategoria;

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nomeCategoria;

    private String areaConhecimento;

    private String descricao;
}
