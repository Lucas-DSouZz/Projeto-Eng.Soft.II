package br.unesp.rc.sistemacadastro.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProdutoDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;
    private String descricao;
    
    @Min(value = 0, message = "O preço não pode ser negativo")
    private double preco;
    
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private int quantidade;
    
    @Min(value = 0, message = "O estoque mínimo não pode ser negativo")
    private int estoqueMinimo;
    private Long fornecedorId;
    private String fornecedorCnpj;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String nome, String descricao, double preco, int quantidade, int estoqueMinimo, Long fornecedorId, String fornecedorCnpj) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
        this.fornecedorId = fornecedorId;
        this.fornecedorCnpj = fornecedorCnpj;
    }
}
