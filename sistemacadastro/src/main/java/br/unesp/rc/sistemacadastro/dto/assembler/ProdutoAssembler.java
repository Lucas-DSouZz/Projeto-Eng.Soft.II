package br.unesp.rc.sistemacadastro.dto.assembler;

import br.unesp.rc.sistemacadastro.dto.ProdutoDTO;
import br.unesp.rc.sistemacadastro.entity.Juridica;
import br.unesp.rc.sistemacadastro.entity.Produto;

/**
* @author Prof. Dr. Frank J. Affonso
*/
public class ProdutoAssembler {

    private ProdutoAssembler() {
    }

    public static Produto dtoToEntityModel(ProdutoDTO dto, Juridica fornecedor) {
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setEstoqueMinimo(dto.getEstoqueMinimo());
        produto.setFornecedor(fornecedor);
        produto.atualizarStatus();

        return produto;
    }
}
