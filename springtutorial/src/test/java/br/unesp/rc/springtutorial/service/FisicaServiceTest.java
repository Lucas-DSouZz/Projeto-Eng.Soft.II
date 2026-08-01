package br.unesp.rc.springtutorial.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.unesp.rc.springtutorial.entity.Fisica;
import br.unesp.rc.springtutorial.utils.InstanceGenerator;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@SpringBootTest
public class FisicaServiceTest {

    private Fisica entity;

    @Autowired
    private FisicaService fs = new FisicaService();

    //@Disabled
    @Test
    @DisplayName("FisicaService.save(Fisica)")
    void testSave() {
        entity = InstanceGenerator.getPessoaFisica( "222.333.444-56" , "user2" );

        System.out.println(entity);

        Fisica f = fs.save(entity);
        System.out.println("----------------------------------------");
        System.out.println(f);
        System.out.println("----------------------------------------");

        assertEquals(entity, f);
    }

    @Disabled
    @Test
    @DisplayName("FisicaService.findByCpf(cpf)")
    void testFindByCpf() {
        entity = InstanceGenerator.getPessoaFisica("222.333.444-55", "user3");

        String cpf = "222.333.444-55";
        Fisica f = fs.findByCpf(cpf);
        System.out.println("----------------------------------------");
        System.out.println("Resultado do findByCPF:");
        System.out.println("----------------------------------------");
        System.out.println(f);
        System.out.println("----------------------------------------");

        assertEquals(entity, f);
    }

    @Disabled
    @Test
    void testDelete() {
        entity = InstanceGenerator.getPessoaFisica("999.888.777-66", "userDelete");

        fs.save(entity);
        fs.delete(entity);

        Fisica f = fs.findByCpf("999.888.777-66");

        assertNull(f);
    }

    @Disabled
    @Test
    void testUpdate() {
        entity = InstanceGenerator.getPessoaFisica("111.222.333-44", "user2");

        fs.save(entity);

        entity.setNome("Novo Nome");

        Fisica atualizada = fs.update(entity);

        assertEquals("Novo Nome", atualizada.getNome());
    }

    @Disabled
    @Test
    @DisplayName("FisicaService.findAll()")
    void testFindAll() {
        System.out.println("findAll");

        Fisica expResult = null;
        System.out.println("----------------------------------------");
        System.out.println("Resultado do findAll:");
        System.out.println("----------------------------------------");
        List<Fisica> result = fs.findAll();
        for (Fisica f : result) {
            System.out.println("----------------------------------------");
            System.out.println("FISICA: " + f);
            System.out.println("----------------------------------------");
        }
        assertNotEquals(expResult, result);
    }
}
