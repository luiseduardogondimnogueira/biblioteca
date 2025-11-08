package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoricoChatRequest {

    @NotBlank
    private String idMessage;

    private String message;

    private LocalDateTime horario;

}
