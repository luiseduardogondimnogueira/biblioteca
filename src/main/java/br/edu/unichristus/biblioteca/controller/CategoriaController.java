package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequest;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.CategoriaResponse;
import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.service.CategoriaService;
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
@RequestMapping("api/v1/categoria")
@Tag(name = "Categoria", description = "Operações relacionadas à manutenção das categorias | Entidade CATEGORIA")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Cadastra os dados de uma nova categoria",
            description = "Cria uma nova categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (validação falhou)")})
    @PostMapping
    public CategoriaResponse create(@Valid @RequestBody CategoriaRequest categoriaRequest) {
        return service.create(categoriaRequest);
    }

    @Operation(summary = "Retorna todas as categorias",
            description = "Lista todas as categorias cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de categorias",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class))))})
    @GetMapping("/all")
    public List<CategoriaResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca uma categoria por id",
            description = "Retorna os dados da categoria especificada pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")})
    @GetMapping("/{id}")
    public CategoriaResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Atualiza os dados de uma categoria existente",
            description = "Atualiza os dados de uma categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")})
    @PutMapping
    public CategoriaResponse update(@Valid @RequestBody CategoriaRequestUpdate categoriaRequestUpdate) {
        return service.update(categoriaRequestUpdate);
    }

    @Operation(
            summary = "Remove uma categoria por id", description = "Exclui a categoria especificada pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "409", description =
                    "A operação não pôde ser concluída porque o recurso está em uso ou possui dependências que impedem sua exclusão.")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long idCategoria) {
        service.deleteById(idCategoria);
    }

}
