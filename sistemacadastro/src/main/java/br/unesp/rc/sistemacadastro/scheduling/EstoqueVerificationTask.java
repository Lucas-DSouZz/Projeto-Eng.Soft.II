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

    // Executa a cada 60.000 milissegundos (1 minuto)
    @Scheduled(fixedRate = 60000)
    public void verificarEstoque() {
        System.out.println("Iniciando verificação periódica de estoque dos produtos...");
        
        List<Produto> produtos = produtoService.findAll();
        
        for (Produto produto : produtos) {
            // A regra de atualizar status já está no ProdutoService (chamada entity.atualizarStatus()),
            // mas findAll no ProdutoService também atualiza em memória. 
            // Precisamos salvar no banco de dados se houve alteração.
            
            // O ProdutoService.update() atualiza o status baseado na quantidade e estoqueMinimo e salva no banco.
            produtoService.update(produto);
        }
        
        System.out.println("Verificação de estoque finalizada. " + produtos.size() + " produtos checados.");
    }
}
