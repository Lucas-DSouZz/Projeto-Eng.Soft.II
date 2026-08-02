package br.unesp.rc.sistemacadastro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FisicaDTO {

    @NotBlank(message = "Usuário é obrigatório")
    private String usuario;
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    private String telefoneResidencial;
    private String telefoneComercial;
    private String celular;
    private String email;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    @NotNull(message = "Data de nascimento é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date dataNascimento;
    private List<EnderecoDTO> endereco;

    public FisicaDTO() {
        this.endereco = new ArrayList<>();
    }

    public FisicaDTO(String usuario, String senha, String telefoneResidencial, String telefoneComercial, String celular, String email, String nome, String cpf, Date dataNascimento, List<EnderecoDTO> endereco) {
        this.usuario = usuario;
        this.senha = senha;
        this.telefoneResidencial = telefoneResidencial;
        this.telefoneComercial = telefoneComercial;
        this.celular = celular;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
}
