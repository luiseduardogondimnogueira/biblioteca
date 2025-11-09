package br.edu.unichristus.biblioteca.domain.dto;

import java.math.BigDecimal;

public record TransacaoResumoPorUsuario(
        Long idUsuario,
        String nomeUsuario,
        long totalDownloads,
        long totalAcessos,
        BigDecimal totalFaturado) {
}
