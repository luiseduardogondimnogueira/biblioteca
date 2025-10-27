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
    private Long idCategoria;

    @Column(unique = true)
    private String nomeCategoria;

    private String areaConhecimento;

    private String descricao;

    // Relacionamento One-to-Many: 1 categoria para muitos livros
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Livro> livros;
}