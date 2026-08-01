package br.unesp.rc.sistemacadastro.resource;

import br.unesp.rc.sistemacadastro.dto.JuridicaDTO;
import br.unesp.rc.sistemacadastro.dto.assembler.JuridicaAssembler;
import br.unesp.rc.sistemacadastro.entity.Juridica;
import br.unesp.rc.sistemacadastro.entity.mapper.JuridicaMapper;
import br.unesp.rc.sistemacadastro.service.JuridicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import jakarta.validation.Valid;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@RestController
@RequestMapping("/juridicas")
public class JuridicaController {

    @Autowired
    private JuridicaService juridicaService;

    @Operation(summary = "Retorna todas as pessoas jurídicas (fornecedores)")
    @GetMapping
    public List<Juridica> getAll() {
        return juridicaService.findAll();
    }

    @Operation(summary = "Retorna uma pessoa jurídica pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fornecedor encontrado!",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Juridica.class)) }),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado!", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Juridica> getById(@PathVariable(value = "id") Long id) {
        Juridica juridica = juridicaService.findById(id);
        if (juridica != null) {
            return ResponseEntity.ok(juridica);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cadastra uma nova pessoa jurídica (fornecedor)")
    @PostMapping
    public ResponseEntity<Juridica> save(@RequestBody @Valid JuridicaDTO dto) {
        Juridica juridica = JuridicaAssembler.dtoToEntityModel(dto);
        Juridica saved = juridicaService.save(juridica);
        if (saved != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Atualiza uma pessoa jurídica pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Juridica> update(@PathVariable(value = "id") Long id, @RequestBody @Valid JuridicaDTO dto) {
        Juridica juridicaExistente = juridicaService.findById(id);
        if (juridicaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Juridica novaJuridica = JuridicaAssembler.dtoToEntityModel(dto);
        JuridicaMapper.update(juridicaExistente, novaJuridica);
        Juridica updated = juridicaService.update(juridicaExistente);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Exclui uma pessoa jurídica pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Juridica juridica = juridicaService.findById(id);
        if (juridica != null) {
            juridicaService.delete(juridica);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}
