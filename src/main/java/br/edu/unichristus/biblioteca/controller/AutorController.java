package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorRequest;
import br.edu.unichristus.biblioteca.domain.dto.AutorRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.dto.LivroResponse;
import br.edu.unichristus.biblioteca.service.AutorService;
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
@RequestMapping("api/v1/autor")
@Tag(name = "Autor", description = "Operações relacionadas à manutenção dos autores | Entidade AUTOR")
public class AutorController {

    @Autowired
    private AutorService service;

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Cadastra os dados de um novo autor",
            description = "Cria um novo autor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (validação falhou)")})
    @PostMapping
    public AutorResponse create(@Valid @RequestBody AutorRequest autorRequest) {
        return service.create(autorRequest);
    }

    @Operation(summary = "Retorna todos os autores",
            description = "Lista todos os autores cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de autores",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class))))})
    @GetMapping("/all")
    public List<AutorResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca um autor por id",
            description = "Retorna os dados do autor especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")})
    @GetMapping("/{id}")
    public AutorResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Atualiza os dados de um autor existente",
            description = "Atualiza os dados de um autor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")})
    @PutMapping
    public AutorResponse update(@Valid @RequestBody AutorRequestUpdate autorRequestUpdate) {
        return service.update(autorRequestUpdate);
    }

    @Operation(
            summary = "Remove um autor por id",
            description = "Exclui o autor especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "409", description =
                    "A operação não pôde ser concluída porque o recurso está em uso ou possui dependências que impedem sua exclusão.")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }


    // ----- FEATURES ----- //


    @Operation(summary = "Busca autores por nome * FEATURE *",
            description = "Pesquisa retorna todos os autores onde o nome contenha o termo informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisa concluída",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class)))),
            @ApiResponse(responseCode = "404", description = "Nenhum autor atende o critério de busca.")})
    @GetMapping("/search")
    public List<AutorResponse> findByNome(@RequestParam(name = "nomeAutor") String nome) {
        return service.findByName(nome);
    }

    @Operation(summary = "Retorna todos os livros de um autor, especificado por id * FEATURE *",
            description = "Retorna todos os livros de um autor, especificado por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livros do autor",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LivroResponse.class)))),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")})
    @GetMapping("/{idAutor}/livros")
    public List<LivroResponse> listarLivrosPorAutor(@PathVariable(name = "idAutor") Long id) {
        List<LivroResponse> livrosDoAutor = livroService.listarLivrosPorAutor(id);
        return livrosDoAutor;
    }

}
