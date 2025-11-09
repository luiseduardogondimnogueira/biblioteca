package br.edu.unichristus.biblioteca.service;

import br.edu.unichristus.biblioteca.domain.dto.LivroRequest;
import br.edu.unichristus.biblioteca.domain.dto.LivroRequestUpdate;
import br.edu.unichristus.biblioteca.domain.dto.LivroResponse;
import br.edu.unichristus.biblioteca.domain.dto.LivroResponseFindAll;
import br.edu.unichristus.biblioteca.domain.model.Autor;
import br.edu.unichristus.biblioteca.domain.model.Categoria;
import br.edu.unichristus.biblioteca.domain.model.Livro;
import br.edu.unichristus.biblioteca.exception.BadRequestException;
import br.edu.unichristus.biblioteca.exception.ResourceConflictException;
import br.edu.unichristus.biblioteca.exception.ResourceNotFoundException;
import br.edu.unichristus.biblioteca.repository.AutorRepository;
import br.edu.unichristus.biblioteca.repository.CategoriaRepository;
import br.edu.unichristus.biblioteca.repository.LivroRepository;
import br.edu.unichristus.biblioteca.repository.TransacaoRepository;
import br.edu.unichristus.biblioteca.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    public LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public LivroResponse create(LivroRequest livroRequest) {
        // VALIDAR (Livro com nome repetido)
        String nomeNovoLivro = livroRequest.getNomeLivro();
        List<Livro> livrosEncontrados = repository.findByNomeLivroIgnoreCase(nomeNovoLivro);
        if (!livrosEncontrados.isEmpty()) {
            throw new BadRequestException(
                    "O livro '" + nomeNovoLivro + "' já está cadastrado.");
        }

        // VALIDAR (Autor inexistente)
        long idAutor = livroRequest.getIdAutor();
        Autor autorPesquisado = autorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + idAutor + " não foi localizado."));

        // VALIDAR (Categoria inexistente)
        long idCategoria = livroRequest.getIdCategoria();
        Categoria categoriaPesquisada = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + idCategoria + " não foi localizada."));

        // MODIFICAR/ATUALIZAR (campos do DTO)
        Livro novoLivro = new Livro();
        novoLivro.setNomeLivro(livroRequest.getNomeLivro());
        novoLivro.setAnoPublicacao(livroRequest.getAnoPublicacao());
        novoLivro.setUrlVisualizacao(livroRequest.getUrlVisualizacao());
        novoLivro.setUrlDownload(livroRequest.getUrlDownload());
        novoLivro.setPreco(livroRequest.getPreco());
        novoLivro.setAutor(autorPesquisado);
        novoLivro.setCategoria(categoriaPesquisada);

        // SALVAR o objeto atualizado
        repository.save(novoLivro);

        return MapperUtil.parseObject(novoLivro, LivroResponse.class);
    }

    public List<LivroResponseFindAll> findAll() {
        List<Livro> livros = repository.findAll();
        return MapperUtil.parseListObjects(livros, LivroResponseFindAll.class);
    }

    public LivroResponse findById(Long id) {
        Livro livroPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O livro com o id " + id + " não foi localizado."));
        return MapperUtil.parseObject(livroPesquisado, LivroResponse.class);
    }

    public LivroResponse update(LivroRequestUpdate livroRequestUpdate) {
        // BUSCAR o objeto
        Long id = livroRequestUpdate.getIdLivro();
        Livro livroAtualizar = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O livro com o id " + id + " não foi localizado para atualização."));

        // VALIDAR (Livro com nome repetido)
        String nomeLivro = livroRequestUpdate.getNomeLivro();
        Optional<Livro> livroComMesmoNome = repository.findFirstByNomeLivroIgnoreCase(livroRequestUpdate.getNomeLivro());
        if (livroComMesmoNome.isPresent() && !livroComMesmoNome.get().getIdLivro().equals(id)) {
            throw new BadRequestException("O livro '" + nomeLivro + "' já está cadastrado.");
        }

        // VALIDAR (Autor inexistente)
        long idAutor = livroRequestUpdate.getIdAutor();
        Autor autorPesquisado = autorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + idAutor + " não foi localizado."));

        // VALIDAR (Categoria inexistente)
        long idCategoria = livroRequestUpdate.getIdCategoria();
        Categoria categoriaPesquisada = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "A categoria com o id " + idCategoria + " não foi localizada."));

        // MODIFICAR/ATUALIZAR (campos do DTO)
        livroAtualizar.setNomeLivro(livroRequestUpdate.getNomeLivro());
        livroAtualizar.setAnoPublicacao(livroRequestUpdate.getAnoPublicacao());
        livroAtualizar.setUrlVisualizacao(livroRequestUpdate.getUrlVisualizacao());
        livroAtualizar.setUrlDownload(livroRequestUpdate.getUrlDownload());
        livroAtualizar.setPreco(livroRequestUpdate.getPreco());
        livroAtualizar.setAutor(autorPesquisado);
        livroAtualizar.setCategoria(categoriaPesquisada);

        // SALVAR o objeto atualizado
        repository.save(livroAtualizar);

        return MapperUtil.parseObject(livroAtualizar, LivroResponse.class);
    }

    public void deleteById(Long id) {
        Livro livroPesquisado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O livro com o id " + id + " não foi localizado."));

        // VALIDAR RELACIONAMENTOS — se existir qualquer transação, não delete!
        boolean temTransacoes = transacaoRepository.existsByLivro_Autor_IdAutor(id);

        if (temTransacoes) {
            throw new ResourceConflictException(
                    "Não é possível excluir o livro de id " + id + ". Existem transações relacionadas a esse livro.");
        }

        // REMOVER o livro
        repository.deleteById(id);
    }


    // ----- FEATURES ----- //


    public List<LivroResponse> listarLivrosPorAutor(long idAutor) {
        Autor autorPesquisado = autorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "O autor com o id " + idAutor + " não foi localizado."));
        String nomeAutor = autorPesquisado.getNomeAutor();

        List<Livro> livros = repository.findByAutorIdAutor(idAutor);
        if (livros.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Nenhum livro cadastrado para autor '" + nomeAutor + "' foi localizado.");
        }

        return MapperUtil.parseListObjects(livros, LivroResponse.class);
    }

}
