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
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoria_id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    private String areaConhecimento;

    // Relacionamento One-to-Many: Uma categoria para muitos livros
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Livro> livros;
}