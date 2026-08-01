package br.unesp.rc.sistemacadastro.repository;

import br.unesp.rc.sistemacadastro.entity.Juridica;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Prof. Dr. Frank J. Affonso
*/
public interface JuridicaRepository extends JpaRepository<Juridica, Long> {

    Juridica findByCnpj(String cnpj);

}
