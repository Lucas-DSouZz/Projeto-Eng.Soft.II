package br.unesp.rc.sistemacadastro.entity.mapper;

import br.unesp.rc.sistemacadastro.entity.Produto;

/**
* @author Prof. Dr. Frank J. Affonso
*/
public class ProdutoMapper {

    private ProdutoMapper() {
    }

    public static void update(Produto produtoUpdate, Produto newProduto) {
        produtoUpdate.setNome(newProduto.getNome());
        produtoUpdate.setDescricao(newProduto.getDescricao());
        produtoUpdate.setPreco(newProduto.getPreco());
        produtoUpdate.setQuantidade(newProduto.getQuantidade());
        produtoUpdate.setEstoqueMinimo(newProduto.getEstoqueMinimo());
        produtoUpdate.setFornecedor(newProduto.getFornecedor());
        produtoUpdate.atualizarStatus();
    }
}
