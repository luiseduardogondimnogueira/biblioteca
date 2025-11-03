package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria create(Categoria categoria) {
        // Validação para id digitado
        if (categoria.getIdCategoria() != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parâmento idCategoria não deve constar na requisição");
        }

        // Validação para nome da categoria ausente
        if (categoria.getNomeCategoria() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parâmento nomeCategoria deve constar na requisição");
        }

        // Validação para nome da categoria em branco
        if (categoria.getNomeCategoria().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parâmetro nomeCategoria é obrigatório e não pode ser deixado em branco");
        }

        // validação para nome da categoria repetida
        var novaCategoria = categoria.getNomeCategoria();
        if (repository.findByNomeCategoriaIgnoreCase(novaCategoria) != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parâmetro nomeCategoria deve ser único e já definido no banco de dados / nomecategoria: " + novaCategoria);
        }
        return repository.save(categoria);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.categoria.notfound",
                        "A categoria com o id pesquisado não foi localizada"));
    }

    public Categoria update(Categoria categoria) {
        // Validação para id digitado
        if (categoria.getIdCategoria() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parâmento idCategoria deve constar na requisição");
        }

        // Validação para nome da categoria ausente
        if (categoria.getNomeCategoria() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parâmento nomeCategoria deve constar na requisição");
        }

        // Validação para nome da categoria em branco
        if (categoria.getNomeCategoria().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O parêmetro nomeCategoria é obrigatório e não pode ser deixado em branco");
        }

        return repository.save(categoria);
    }

    public void deleteById(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND,
                        "unichristus.service.categoria.notfound",
                        "A categoria com o id pesquisado não foi localizada"));
        repository.deleteById(id);
    }

}
