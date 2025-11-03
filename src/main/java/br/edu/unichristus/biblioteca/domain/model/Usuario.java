package br.edu.unichristus.biblioteca.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;

    // Relacionamento One-to-Many com Transacao: 1 Usuario pode ter muitas Transacoes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

}
