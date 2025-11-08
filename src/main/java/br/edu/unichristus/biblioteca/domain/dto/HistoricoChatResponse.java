package br.edu.unichristus.biblioteca.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoricoChatResponse {

    private long idSession;

    private String idMessage;

    private String message;

    private LocalDateTime horario;
}
