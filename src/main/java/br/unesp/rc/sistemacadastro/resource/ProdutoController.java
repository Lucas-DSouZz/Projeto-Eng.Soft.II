package br.unesp.rc.sistemacadastro.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.sistemacadastro.dto.ProdutoDTO;
import br.unesp.rc.sistemacadastro.dto.assembler.ProdutoAssembler;
import br.unesp.rc.sistemacadastro.entity.Juridica;
import br.unesp.rc.sistemacadastro.entity.Produto;
import br.unesp.rc.sistemacadastro.entity.mapper.ProdutoMapper;
import br.unesp.rc.sistemacadastro.service.JuridicaService;
import br.unesp.rc.sistemacadastro.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private JuridicaService juridicaService;

    @Operation(summary = "Retorna todos os produtos")
    @GetMapping
    public List<Produto> getAll() {
        return produtoService.findAll();
    }

    @Operation(summary = "Retorna um produto pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado!",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado!", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable(value = "id") Long id) {
        Produto produto = produtoService.findById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cadastra um novo produto")
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoDTO dto) {
        Juridica fornecedor = null;
        if (dto.getFornecedorId() != null) {
            fornecedor = juridicaService.findById(dto.getFornecedorId());
            if (fornecedor == null) {
                return ResponseEntity.badRequest().build();
            }
        } else if (dto.getFornecedorCnpj() != null) {
            fornecedor = juridicaService.findByCnpj(dto.getFornecedorCnpj());
            if (fornecedor == null) {
                return ResponseEntity.badRequest().build();
            }
        }

        Produto produto = ProdutoAssembler.dtoToEntityModel(dto, fornecedor);
        Produto saved = produtoService.save(produto);
        if (saved != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Atualiza um produto pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProdutoDTO dto) {
        Produto produtoExistente = produtoService.findById(id);
        if (produtoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Juridica fornecedor = null;
        if (dto.getFornecedorId() != null) {
            fornecedor = juridicaService.findById(dto.getFornecedorId());
            if (fornecedor == null) {
                return ResponseEntity.badRequest().build();
            }
        } else if (dto.getFornecedorCnpj() != null) {
            fornecedor = juridicaService.findByCnpj(dto.getFornecedorCnpj());
            if (fornecedor == null) {
                return ResponseEntity.badRequest().build();
            }
        }

        Produto novoProduto = ProdutoAssembler.dtoToEntityModel(dto, fornecedor);
        ProdutoMapper.update(produtoExistente, novoProduto);
        Produto updated = produtoService.update(produtoExistente);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Exclui um produto pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        boolean deleted = produtoService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}
