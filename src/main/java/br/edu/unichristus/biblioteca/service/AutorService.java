package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.AutorRequest;
import br.edu.unichristus.biblioteca.domain.dto.AutorRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.exception.BadRequestException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorResponse create(AutorRequest autorRequest) {
        Autor novoAutor = MapperUtil.parseObject(autorRequest, Autor.class);
        repository.save(novoAutor);
        return MapperUtil.parseObject(novoAutor, AutorResponse.class);
    }

    public List<AutorResponse> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), AutorResponse.class);

    }

    public AutorResponse findById(Long id) {
        Autor autorPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + id + " não foi localizado."));
        return MapperUtil.parseObject(autorPesquisado, AutorResponse.class);

    }

    public List<AutorResponse> findByName(String nome) {
        List<Autor> autores = repository.findByNomeAutorContainingIgnoreCase(nome);
        if (autores.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Nenhum autor cujo nome contenha '" + nome + "' foi localizado.");
        }
        return MapperUtil.parseListObjects(autores, AutorResponse.class);
    }

    public AutorResponse update(AutorRequestUpdate autorRequestUpdate) {
        Long id = autorRequestUpdate.getIdAutor();

        // 1. BUSCAR o objeto existente
        Autor autorAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + id + " não foi localizado para atualização."));

        // 2. VALIDAR

        // 3. MODIFICAR/ATUALIZAR (apenas campos do DTO)
        autorAtualizar.setNomeAutor(autorRequestUpdate.getNomeAutor());
        autorAtualizar.setDataNascimento(autorRequestUpdate.getDataNascimento());
        autorAtualizar.setNacionalidade(autorRequestUpdate.getNacionalidade());
        autorAtualizar.setBiografia(autorRequestUpdate.getBiografia());

        // 4. SALVAR o objeto modificado
        repository.save(autorAtualizar);
        return MapperUtil.parseObject(autorAtualizar, AutorResponse.class);
    }

    public void deleteById(Long id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + id + " não foi localizado."));
        repository.deleteById(id);
    }

}