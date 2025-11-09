package br.edu.unichristus.biblioteca.domain.dto;

import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoResponse {

    private Long idTransacao;

    private String nomeUsuario;

    private String nomeLivro;

    private TipoTransacao tipo;

    private LocalDateTime dataHora;

    private String urlVisualizacao;

    private String urlDownload;

    private BigDecimal valor;

}
