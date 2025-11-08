package br.edu.unichristus.biblioteca.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(max = 100, message = "O nome do usuário deve ter no máximo 100 caracteres")
    private String nomeUsuario;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Size(min = 8, message = "O telefone deve ter no mínimo 8 dígitos")
    private String telefone;
}
