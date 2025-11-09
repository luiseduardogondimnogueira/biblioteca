package br.edu.unichristus.biblioteca.domain.dto;

import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoRequest {

    @NotNull(message = "O id do usuário é obrigatório")
    private Long idUsuario;

    @NotNull(message = "O id do livro é obrigatório")
    private Long idLivro;

    @NotNull(message = "O tipo de transação é obrigatório")
    private TipoTransacao tipo;

    @NotNull(message = "A data/hora da transação é obrigatória")
    private LocalDateTime dataHora;

    private String urlVisualizacao;

    private String urlDownload;

    private BigDecimal valor;
}
