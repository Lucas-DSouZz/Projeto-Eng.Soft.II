package br.unesp.rc.sistemacadastro.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * @author Prof. Dr. Frank J. Affonso
*/
@Entity
@Table(name = "Produto")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    private double preco;

    private int quantidade;

    private int estoqueMinimo;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Juridica fornecedor;

    @Enumerated(EnumType.STRING)
    private StatusProduto status;

    public Produto() {
    }

    public void atualizarStatus() {
        if (this.quantidade <= this.estoqueMinimo) {
            this.status = StatusProduto.ESTOQUE_BAIXO;
        } else {
            this.status = StatusProduto.NORMAL;
        }
    }
}
