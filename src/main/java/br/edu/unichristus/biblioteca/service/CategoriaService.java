package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequest;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaResponse;
import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.exception.ResourceConflictException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.CategoriaRepository;
import br.edu.unichristus.biblioteca.repository.LivroRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    public CategoriaResponse create(CategoriaRequest categoriaRequest) {
        Categoria novaCategoria = MapperUtil.parseObject(categoriaRequest, Categoria.class);
        repository.save(novaCategoria);
        return MapperUtil.parseObject(novaCategoria, CategoriaResponse.class);
    }

    public List<CategoriaResponse> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), CategoriaResponse.class);
    }

    public CategoriaResponse findById(Long id) {
        Categoria categoriaPesquisada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + id + " não foi localizada."));
        return MapperUtil.parseObject(categoriaPesquisada, CategoriaResponse.class);
    }

    public CategoriaResponse update(CategoriaRequestUpdate categoriaRequestUpdate) {
        // BUSCAR o objeto
        Long id = categoriaRequestUpdate.getIdCategoria();
        Categoria categoriaAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + id + " não foi localizada para atualização."));

        // VALIDAR (Regras de negócio)

        // MODIFICAR/ATUALIZAR (campos do DTO)
        categoriaAtualizar.setNomeCategoria(categoriaRequestUpdate.getNomeCategoria());
        categoriaAtualizar.setAreaConhecimento(categoriaRequestUpdate.getAreaConhecimento());
        categoriaAtualizar.setDescricao(categoriaRequestUpdate.getDescricao());

        // SALVAR o objeto atualizado
        repository.save(categoriaAtualizar);

        return MapperUtil.parseObject(categoriaAtualizar, CategoriaResponse.class);
    }

    public void deleteById(Long id) {
        Categoria categoriaPesquisada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + id + " não foi localizada."));

        // VALIDAR RELACIONAMENTOS — se existir qualquer livro, não delete!
        boolean temLivros = livroRepository.existsByCategoria_IdCategoria(id);

        if (temLivros) {
            throw new ResourceConflictException(
                    "Não é possível excluir a categoria de id " + id + ". Existem livros cadastrados nessa categoria");
        }

        // REMOVER a categoria
        repository.deleteById(id);
    }

}
