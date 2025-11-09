package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutorRequestUpdate {

    @NotNull(message = "O id do autor é obrigatório")
    private Long idAutor;

    @NotBlank(message = "O nome do autor é obrigatório")
    @Size(max = 100, message = "O nome do autor deve ter no máximo 100 caracteres")
    private String nomeAutor;

    private LocalDate dataNascimento;

    private String nacionalidade;

    @Size(max = 1000, message = "A url da biografia deve ter no máximo 1000 caracteres")
    private String biografia;
}