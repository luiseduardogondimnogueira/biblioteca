package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoRequest;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.TransacaoResponse;
import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import br.edu.unichristus.biblioteca.domain.model.Transacao;
import br.edu.unichristus.biblioteca.service.TransacaoService;
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
@RequestMapping("api/v1/transacao")
@Tag(name = "Transação", description = "Operações relacionadas às transações de acesso e download de livros | Entidade TRANSACAO")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @Operation(summary = "Cadastra os dados de uma nova transação",
            description = "Cria uma nova transação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transação criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (validação falhou)")})
    @PostMapping
    public TransacaoResponse create(@Valid @RequestBody TransacaoRequest transacaoRequest) {
        return service.create(transacaoRequest);
    }

    @Operation(summary = "Retorna todas as transação",
            description = "Lista todas as transação cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de transações",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class))))})
    @GetMapping("/all")
    public List<TransacaoResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca uma transação por id",
            description = "Retorna os dados da transação especificada pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transação encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada")})
    @GetMapping("/{id}")
    public TransacaoResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Atualiza os dados de uma transação existente",
            description = "Atualiza os dados de uma transação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transação atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada")})
    @PutMapping
    public TransacaoResponse update(@Valid @RequestBody TransacaoRequestUpdate transacaoRequestUpdate) {
        return service.update(transacaoRequestUpdate);
    }

    @Operation(
            summary = "Remove uma transação por id", description = "Exclui a transação especificada pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "transação excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "transação não encontrada")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }


    // ----- FEATURES ----- //


    @Operation(summary = "Busca transações de um usuário",
            description = "Retorna todas as transações associadas a um usuário especificada pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de transações",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")})
    @GetMapping("/usuario/{id}")
    public List<TransacaoResponse> findByUsuario(
            @PathVariable(name = "id") Long idUsuario,
            @RequestParam(required = false) TipoTransacao tipo) {
        return service.findTransacaoByUsuario(idUsuario, tipo);
    }

}
