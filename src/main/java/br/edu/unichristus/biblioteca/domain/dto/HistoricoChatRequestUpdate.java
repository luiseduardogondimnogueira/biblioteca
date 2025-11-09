package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoricoChatRequestUpdate {

    @NotNull(message = "O id da sessão é obrigatório")
    private long idSession;

    @NotBlank(message = "O id da messagem é obrigatório")
    private String idMessage;

    private String message;

    private LocalDateTime horario;
}
