package br.edu.unichristus.biblioteca.domain.dto;

import lombok.Data;

@Data
public class CategoriaDTO {

    private Long idCategoria;

    private String nomeCategoria;

    private String areaConhecimento;

    private String descricao;

}
