package br.unesp.rc.sistemacadastro.repository;

import br.unesp.rc.sistemacadastro.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Prof. Dr. Frank J. Affonso
*/
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
