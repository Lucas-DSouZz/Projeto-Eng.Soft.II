package br.unesp.rc.sistemacadastro.resource;

import br.unesp.rc.sistemacadastro.dto.FisicaDTO;
import br.unesp.rc.sistemacadastro.dto.assembler.FisicaAssembler;
import br.unesp.rc.sistemacadastro.entity.Fisica;
import br.unesp.rc.sistemacadastro.entity.mapper.FisicaMapper;
import br.unesp.rc.sistemacadastro.service.FisicaService;
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
@RequestMapping("/fisicas")
public class FisicaResource {

    @Autowired
    private FisicaService fisicaService;

    @Operation(summary = "Retorna todas as pessoas físicas (clientes)")
    @GetMapping
    public List<Fisica> getAllFisica() {
        return fisicaService.findAll();
    }

    @Operation(summary = "Retorna uma pessoa física pelo ID ou CPF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa-física encontrada!",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Fisica.class)) }),
        @ApiResponse(responseCode = "404", description = "Pessoa-física não encontrada!", content = @Content)
    })
    @GetMapping("/{idOrCpf}")
    public ResponseEntity<Fisica> getFisicaByIdOrCpf(@PathVariable(value = "idOrCpf") String idOrCpf) {
        Fisica fisica = null;
        try {
            Long id = Long.parseLong(idOrCpf);
            fisica = fisicaService.findById(id);
        } catch (NumberFormatException e) {
        }

        if (fisica == null) {
            fisica = fisicaService.findByCpf(idOrCpf);
        }

        if (fisica != null) {
            return ResponseEntity.ok(fisica);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cadastra uma nova pessoa física (cliente)")
    @PostMapping
    public ResponseEntity<Fisica> saveFisica(@RequestBody @Valid FisicaDTO fisicaDto) {
        Fisica fisica = FisicaAssembler.dtoToEntityModel(fisicaDto);
        Fisica fisicaInsert = fisicaService.save(fisica);
        if (fisicaInsert != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(fisicaInsert);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Atualiza uma pessoa física pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Fisica> updateById(@PathVariable(value = "id") Long id, @RequestBody @Valid FisicaDTO fisicaDto) {
        Fisica fisicaUpdate = fisicaService.findById(id);
        if (fisicaUpdate == null && fisicaDto.getCpf() != null) {
            fisicaUpdate = fisicaService.findByCpf(fisicaDto.getCpf());
        }

        if (fisicaUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        Fisica newFisica = FisicaAssembler.dtoToEntityModel(fisicaDto);
        FisicaMapper.update(fisicaUpdate, newFisica);
        Fisica fisicaUpdated = fisicaService.update(fisicaUpdate);

        return ResponseEntity.ok(fisicaUpdated);
    }

    @Operation(summary = "Exclui uma pessoa física pelo ID ou CPF")
    @DeleteMapping("/{idOrCpf}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "idOrCpf") String idOrCpf) {
        Fisica fisica = null;
        try {
            Long id = Long.parseLong(idOrCpf);
            fisica = fisicaService.findById(id);
        } catch (NumberFormatException e) {
        }

        if (fisica == null) {
            fisica = fisicaService.findByCpf(idOrCpf);
        }

        if (fisica != null) {
            fisicaService.delete(fisica);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }
}
