package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.UsuarioRequest;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.UsuarioResponse;
import br.edu.unichristus.biblioteca.domain.model.Usuario;
import br.edu.unichristus.biblioteca.exception.BadRequestException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.UsuarioRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository repository;

    public UsuarioResponse create(UsuarioRequest usuarioRequest) {

        // VALIDAR (e-mail Repetido)
        if (repository.findByEmail(usuarioRequest.getEmail()).isPresent()) {
            throw new BadRequestException("O e-mail '" + usuarioRequest.getEmail() + "' já está sendo usado por outro usuário.");
        }

        Usuario novoUsuario = MapperUtil.parseObject(usuarioRequest, Usuario.class);
        repository.save(novoUsuario);
        return MapperUtil.parseObject(novoUsuario, UsuarioResponse.class);
    }

    public List<UsuarioResponse> findAll() {
        return MapperUtil.parseListObjects(repository.findAll(), UsuarioResponse.class);
    }

    public UsuarioResponse findById(Long id) {
        Usuario usuarioPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O usuário com o id " + id + " não foi localizado."));
        return MapperUtil.parseObject(usuarioPesquisado, UsuarioResponse.class);
    }

    public UsuarioResponse update(UsuarioRequestUpdate usuarioRequestUpdate) {

        // 1. BUSCAR o objeto
        Long id = usuarioRequestUpdate.getIdUsuario();
        Usuario usuarioAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O usuário com o id " + id + " não foi localizado para atualização."));

        // 2. VALIDAR (e-mail Repetido)
        Optional<Usuario> usuarioEmailRepetido = repository.findByEmail(usuarioRequestUpdate.getEmail());
        if (usuarioEmailRepetido.isPresent() && !usuarioEmailRepetido.get().getIdUsuario().equals(id)) {
            throw new BadRequestException("O e-mail '" + usuarioRequestUpdate.getEmail() + "' já está sendo usado por outro usuário.");
        }

        // 3. MODIFICAR/ATUALIZAR (apenas campos do DTO)
        usuarioAtualizar.setNomeUsuario(usuarioRequestUpdate.getNomeUsuario());
        usuarioAtualizar.setEmail(usuarioRequestUpdate.getEmail());
        usuarioAtualizar.setTelefone(usuarioRequestUpdate.getTelefone());

        // 4. SALVAR o objeto atualizado
        repository.save(usuarioAtualizar);
        return MapperUtil.parseObject(usuarioAtualizar, UsuarioResponse.class);
    }

    public void deleteById(Long id) {
        Usuario usuarioPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O usuário com o id " + id + " não foi localizado."));
        repository.deleteById(id);
    }

}
