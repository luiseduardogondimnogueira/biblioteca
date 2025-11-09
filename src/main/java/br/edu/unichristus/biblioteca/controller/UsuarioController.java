package br.edu.unichristus.biblioteca.controller;

import br.edu.unichristus.biblioteca.domain.dto.AutorResponse;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioRequest;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioResponse;
import br.edu.unichristus.biblioteca.service.UsuarioService;
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
@RequestMapping("api/v1/usuario")
@Tag(name = "Usuário", description = "Operações relacionadas à manutenção de usuários | Entidade USUARIO")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Cadastra os dados de um novo usuário",
            description = "Cria um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (validação falhou)")})
    @PostMapping
    public UsuarioResponse create(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return service.create(usuarioRequest);
    }

    @Operation(summary = "Retorna todos os usuários",
            description = "Lista todos os usuários cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class))))})
    @GetMapping("/all")
    public List<UsuarioResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca um usuário por id",
            description = "Retorna os dados do usuário especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")})
    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @Operation(
            summary = "Atualiza os dados de um usuário existente",
            description = "Atualiza os dados de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")})
    @PutMapping
    public UsuarioResponse update(@Valid @RequestBody UsuarioRequestUpdate usuarioRequestUpdate) {
        return service.update(usuarioRequestUpdate);
    }

    @Operation(
            summary = "Remove um usuário por id",
            description = "Exclui o usuário especificado pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "409", description =
                    "A operação não pôde ser concluída porque o recurso está em uso ou possui dependências que impedem sua exclusão.")})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

}