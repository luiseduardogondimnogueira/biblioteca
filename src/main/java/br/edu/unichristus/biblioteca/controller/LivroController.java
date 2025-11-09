package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.*;
import br.edu.unichristus.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/livro")
@Tag(name = "Livro", description = "Operações relacionadas à manutenção dos livros | Entidade LIVRO")
public class LivroController {

    @Autowired
    private LivroService service;

    @Operation(summary = "Cadastra os dados de um novo livro",
            description = "Cria um novo livro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (validação falhou)")})
    @PostMapping
    public LivroResponse create(@Valid @RequestBody LivroRequest livroRequest) {
        return service.create(livroRequest);
    }

    @Operation(summary = "Retorna todos os livros",
            description = "Lista todos os livros cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de livros",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class))))})
    @GetMapping("/all")
    public List<LivroResponseFindAll> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca um livro por id",
            description = "Retorna os dados do livro especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")})
    @GetMapping("/{id}")
    public LivroResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Atualiza os dados de um livro existente",
            description = "Atualiza os dados de um livro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")})
    @PutMapping
    public LivroResponse update(@Valid @RequestBody LivroRequestUpdate livroRequestUpdate) {
        return service.update(livroRequestUpdate);
    }

    @Operation(
            summary = "Remove um livro por id",
            description = "Exclui o livro especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "409", description =
                    "A operação não pôde ser concluída porque o recurso está em uso ou possui dependências que impedem sua exclusão.")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }
}
