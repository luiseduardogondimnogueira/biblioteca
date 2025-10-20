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
    private Long usuario_id;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<HistoricoChat> historicoChat;
///    A anotação @OneToMany indica que um Usuário pode ter múltiplos registros em HistoricoChat.
///    mappedBy = "usuario" define que a responsabilidade pelo mapeamento (ou seja, a coluna de
///    chave estrangeira) está no lado Many, que é o campo usuario na classe HistoricoChat.
///    cascade = CascadeType.ALL significa que, se você excluir um Usuário, todos os seus
///    registros de HistoricoChat relacionados também serão excluídos automaticamente.
}