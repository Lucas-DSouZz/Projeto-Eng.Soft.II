package br.unesp.rc.sistemacadastro.scheduling;

import br.unesp.rc.sistemacadastro.entity.Produto;
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

    @Autowired
    private ProdutoService produtoService;

    // Executa a cada 15.000 milissegundos (15 segundos)
    @Scheduled(fixedRate = 15000)
    public void verificarEstoque() {
        System.out.println("Iniciando verificação periódica de estoque dos produtos...");
        
        List<Produto> produtos = produtoService.findAll();
        
        for (Produto produto : produtos) {
            produtoService.update(produto);
        }
        
        System.out.println("Verificação de estoque finalizada. " + produtos.size() + " produtos checados.");
    }
}
