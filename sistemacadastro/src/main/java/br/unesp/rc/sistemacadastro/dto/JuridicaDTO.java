package br.unesp.rc.sistemacadastro.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JuridicaDTO {

    @NotBlank(message = "Usuário é obrigatório")
    private String usuario;
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    private String telefoneResidencial;
    private String telefoneComercial;
    private String celular;
    private String email;
    @NotBlank(message = "Nome da empresa é obrigatório")
    private String nome;
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    private List<EnderecoDTO> endereco;

    public JuridicaDTO() {
        this.endereco = new ArrayList<>();
    }

    public JuridicaDTO(String usuario, String senha, String telefoneResidencial, String telefoneComercial, String celular, String email, String nome, String cnpj, List<EnderecoDTO> endereco) {
        this.usuario = usuario;
        this.senha = senha;
        this.telefoneResidencial = telefoneResidencial;
        this.telefoneComercial = telefoneComercial;
        this.celular = celular;
        this.email = email;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }
}
