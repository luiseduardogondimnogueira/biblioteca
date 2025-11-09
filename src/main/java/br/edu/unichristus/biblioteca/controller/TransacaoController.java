package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.*;
import br.edu.unichristus.biblioteca.domain.model.TipoTransacao;
import br.edu.unichristus.biblioteca.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @Operation(summary = "Busca transações de um usuário * FEATURE *",
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

    @Operation(summary = "Busca o total de transações de um livro * FEATURE *",
            description = "Retorna o número total de transações associadas a um livro de um determinado tipo (ACESSO ou DOWNLOAD).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Total de transações",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "integer", example = "15"))),
            @ApiResponse(responseCode = "404", description = "Livro ou transações do tipo especificado não encontrados")})
    @GetMapping("/livro/{id}/{tipo}")
    public ResponseEntity<Long> countByLivroAndTipo(
            @Parameter(description = "ID do livro", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Tipo da transação (ACESSO ou DOWNLOAD)", example = "DOWNLOAD")
            @PathVariable TipoTransacao tipo) {
        long total = service.countByLivroAndTipo(id, tipo);
        return ResponseEntity.ok(total);
    }

    @Operation(
            summary = "Resumo de transações por usuário * FEATURE *",
            description = "Retorna totais de acessos, downloads e faturamento (soma de valores) de todas as transações, por usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatório retornado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum resumo encontrado",
                    content = @Content)})
    @GetMapping("/resumo")
    public ResponseEntity<List<TransacaoResumoPorUsuario>> findResumoByUsuario() {
        List<TransacaoResumoPorUsuario> resumo = service.findResumoByUsuario();
        return ResponseEntity.ok(resumo);
    }

}
