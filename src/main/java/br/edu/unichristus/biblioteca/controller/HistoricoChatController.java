package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatRequest;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.HistoricoChatResponse;
import br.edu.unichristus.biblioteca.domain.model.HistoricoChat;
import br.edu.unichristus.biblioteca.service.HistoricoChatService;
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
@RequestMapping("api/v1/historicochat")
@Tag(name = "HistoricoChat", description = "Operações relacionadas à manutenção dos registros de comunicação via chat | Entidade HISTORICO_CHAT")
public class HistoricoChatController {

    @Autowired
    private HistoricoChatService service;

    @Operation(summary = "Cadastra os dados de um novo registro de comunicação via chat",
            description = "Cria um novo registro de comunicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro de comunicação criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (validação falhou)")})
    @PostMapping
    public HistoricoChatResponse create(@Valid @RequestBody HistoricoChatRequest historicoChatRequest) {
        return service.create(historicoChatRequest);
    }

    @Operation(summary = "Retorna todos os registro de comunicação",
            description = "Lista todos os registros de comunicação via chat cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista dos registros de comunicação",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class))))})
    @GetMapping("/all")
    public List<HistoricoChatResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca um registro de comunicação por id",
            description = "Retorna os dados de um registro de comunicação selecionado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")})
    @GetMapping("/{id}")
    public HistoricoChatResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Atualiza os dados de um registro de comunicação existente",
            description = "Atualiza os dados de um registro de comunicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")})
    @PutMapping
    public HistoricoChatResponse update(@Valid @RequestBody HistoricoChatRequestUpdate historicoChatRequestUpdate) {
        return service.update(historicoChatRequestUpdate);
    }

    @Operation(
            summary = "Remove um registro de comunicação por id",
            description = "Exclui um registro de comunicação especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }
}
