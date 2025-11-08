package br.edu.unichristus.biblioteca.domain.dto;

import lombok.Data;

@Data
public class UsuarioResponse {

    private Long idUsuario;

    private String nomeUsuario;

    private String email;

    private String telefone;

}
