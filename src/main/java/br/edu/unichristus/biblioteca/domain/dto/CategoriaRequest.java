package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nomeCategoria;

    private String areaConhecimento;

    private String descricao;

}
