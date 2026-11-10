package senai.hyan.comprebem.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import senai.hyan.comprebem.entity.Produto;
import senai.hyan.comprebem.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public PagedModel<Produto> listar(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return new PagedModel<>(repository.findAll(PageRequest.of(page, size, Sort.by("nome"))));
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(this::naoEncontrado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@RequestBody Produto produto) {
        validar(produto);
        produto.setId(null);
        return repository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto editar(@PathVariable UUID id, @RequestBody Produto produto) {
        if (!repository.existsById(id)) {
            throw naoEncontrado();
        }
        validar(produto);
        produto.setId(id);
        return repository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        if (!repository.existsById(id)) {
            throw naoEncontrado();
        }
        repository.deleteById(id);
    }

    private void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw invalido("O nome do produto é obrigatório.");
        }
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw invalido("O preço deve ser maior que zero.");
        }
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw invalido("A quantidade é obrigatória e não pode ser negativa.");
        }
    }

    private ResponseStatusException naoEncontrado() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
    }

    private ResponseStatusException invalido(String mensagem) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
    }
}
