package br.unesp.rc.sistemacadastro.service;

import br.unesp.rc.sistemacadastro.entity.Fisica;
import br.unesp.rc.sistemacadastro.repository.FisicaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@Service
public class FisicaService {

    @Autowired
    private FisicaRepository repository;

    public FisicaService() {
    }

    public Fisica save(Fisica entity) {
        Fisica persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public Fisica findById(Long id) {
        if (repository != null && id != null) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    public Fisica findByCpf(String cpf) {
        Fisica insertedEntity = null;

        if (repository != null) {
            insertedEntity = repository.findByCpf(cpf);
        }

        return insertedEntity;
    }

    public void delete(Fisica entity) {

        if (repository != null) {
            repository.delete(entity);
        }
    }

    public boolean deleteById(Long id) {
        if (repository != null && id != null && repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Fisica update(Fisica entity) {

        Fisica persistedEntity = null;

        if (repository != null) {
            persistedEntity = repository.save(entity);
        }

        return persistedEntity;
    }

    public List<Fisica> findAll() {
        List<Fisica> list = null;

        if (repository != null) {
            list = repository.findAll();
        }

        return list != null ? list : new ArrayList<>();
    }
}
