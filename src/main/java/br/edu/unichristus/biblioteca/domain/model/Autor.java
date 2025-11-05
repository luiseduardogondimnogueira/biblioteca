package br.edu.unichristus.biblioteca.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(nullable = false, length = 100)
    private String nomeAutor;

    private LocalDate dataNascimento;

    private String nacionalidade;

    @Column(length = 1000)
    private String biografia;

    // Relacionamento One-to-Many: Um Autor pode ter muitos Livros
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;

//    A anotação @OneToMany indica que um Autor pode ter múltiplos registros de Livros.
//    mappedBy = "autor" define que a responsabilidade pelo mapeamento (ou seja, a coluna de
//    chave estrangeira) está no lado Many, que é o campo "autor" na classe Livro.
//    cascade = CascadeType.ALL significa que, se você excluir um Autor, todos os seus
//    registros relacionados em Livros também serão excluídos automaticamente.

}
