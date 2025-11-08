package br.edu.unichristus.biblioteca.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AutorResponse {

    private Long idAutor;

    private String nomeAutor;

    private LocalDate dataNascimento;

    private String nacionalidade;

    private String biografia;
}
