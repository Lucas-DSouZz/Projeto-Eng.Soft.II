package br.unesp.rc.sistemacadastro.scheduling;

import br.unesp.rc.sistemacadastro.entity.Produto;
import br.unesp.rc.sistemacadastro.entity.StatusProduto;
import br.unesp.rc.sistemacadastro.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por executar rotinas periódicas de verificação de estoque.
 */
@Component
public class EstoqueVerificationTask {

    private static final String LINE = "=".repeat(62);
    private static final String ROW_FORMAT = "%-32s %10s %12s   %-13s%n";

    @Autowired
    private ProdutoService produtoService;

    // Executa a cada 15.000 milissegundos (15 segundos)
    @Scheduled(fixedRate = 15000)
    public void verificarEstoque() {
        List<Produto> produtos = produtoService.findAll();

        for (Produto produto : produtos) {
            produtoService.update(produto);
        }

        imprimirRelatorio(produtos);
    }

    private void imprimirRelatorio(List<Produto> produtos) {
        System.out.println(LINE);
        System.out.println("Verificação automática de estoque");
        System.out.println(LINE);

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            System.out.println(LINE);
            return;
        }

        System.out.printf(ROW_FORMAT, "Produto", "Qtd.", "Estoque Min.", "Status");
        System.out.println("-".repeat(62));

        long estoqueBaixo = 0;
        for (Produto produto : produtos) {
            boolean baixo = produto.getStatus() == StatusProduto.ESTOQUE_BAIXO;
            String status = baixo ? produto.getStatus() + " !" : produto.getStatus().toString();
            System.out.printf(ROW_FORMAT, produto.getNome(), produto.getQuantidade(),
                    produto.getEstoqueMinimo(), status);
            if (baixo) {
                estoqueBaixo++;
            }
        }

        System.out.println("-".repeat(62));
        System.out.printf("Total: %d produto(s) verificado(s) | %d em estoque baixo%n",
                produtos.size(), estoqueBaixo);
        System.out.println(LINE);
    }
}
