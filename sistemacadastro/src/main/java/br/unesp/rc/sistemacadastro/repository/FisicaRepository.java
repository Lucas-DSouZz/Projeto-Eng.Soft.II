package br.unesp.rc.sistemacadastro.repository;

import br.unesp.rc.sistemacadastro.entity.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @author Prof. Dr. Frank J. Affonso
*/

public interface FisicaRepository extends JpaRepository<Fisica, Long> {

    Fisica findByCpf(String cpf);

}
