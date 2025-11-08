package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequest;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaResponse;
import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.CategoriaRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

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

        // 1. BUSCAR o objeto
        Long id = categoriaRequestUpdate.getIdCategoria();
        Categoria categoriaAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + id + " não foi localizada para atualização."));

        // 2. VALIDAR (Regras de negócio)

        // 3. MODIFICAR/ATUALIZAR (apenas campos do DTO)
        categoriaAtualizar.setNomeCategoria(categoriaRequestUpdate.getNomeCategoria());
        categoriaAtualizar.setAreaConhecimento(categoriaRequestUpdate.getAreaConhecimento());
        categoriaAtualizar.setDescricao(categoriaRequestUpdate.getDescricao());

        // 4. SALVAR o objeto atualizado
        repository.save(categoriaAtualizar);
        return MapperUtil.parseObject(categoriaAtualizar, CategoriaResponse.class);
    }

    public void deleteById(Long id) {
        Categoria categoriaPesquisada = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + id + " não foi localizada."));
        repository.deleteById(id);
    }

}
