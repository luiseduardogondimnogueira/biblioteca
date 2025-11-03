package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.model.Usuario;
import br.edu.unichristus.biblioteca.exception.ApiException;
import br.edu.unichristus.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository repository;

    public Usuario create(Usuario usuario) {
        // Validação para id digitado
        if (usuario.getIdUsuario() != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parâmento idUsuario não deve constar na requisição");
        }

        // Validação para nome da usuario ausente
        if (usuario.getNomeUsuario() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parâmento nomeUsuario deve constar na requisição");
        }

        // Validação para nome da usuario em branco
        if (usuario.getNomeUsuario().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parâmetro nomeUsuario é obrigatório e não pode ser deixado em branco");
        }

        // validação para nome da usuario repetida
        var novaUsuario = usuario.getNomeUsuario();
        if (repository.findByNomeUsuarioIgnoreCase(novaUsuario) != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parâmetro nomeUsuario deve ser único e já definido no banco de dados / nomeusuario: " + novaUsuario);
        }
        return repository.save(usuario);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.usuario.notfound",
                        "A usuario com o id pesquisado não foi localizada"));
    }

    public Usuario update(Usuario usuario) {
        // Validação para id digitado
        if (usuario.getIdUsuario() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parâmento idUsuario deve constar na requisição");
        }

        // Validação para nome da usuario ausente
        if (usuario.getNomeUsuario() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parâmento nomeUsuario deve constar na requisição");
        }

        // Validação para nome da usuario em branco
        if (usuario.getNomeUsuario().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.usuario.badrequest",
                    "O parêmetro nomeUsuario é obrigatório e não pode ser deixado em branco");
        }

        return repository.save(usuario);
    }

    public void deleteById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ApiException(
                        HttpStatus.NOT_FOUND,
                        "unichristus.service.usuario.notfound",
                        "A usuario com o id pesquisado não foi localizada"));
        repository.deleteById(id);
    }

}
