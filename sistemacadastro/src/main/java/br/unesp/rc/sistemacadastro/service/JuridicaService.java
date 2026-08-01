package br.unesp.rc.sistemacadastro.service;

import br.unesp.rc.sistemacadastro.entity.Juridica;
import br.unesp.rc.sistemacadastro.repository.JuridicaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@Service
public class JuridicaService {

    @Autowired
    private JuridicaRepository repository;

    public JuridicaService() {
    }

    public Juridica save(Juridica entity) {
        Juridica persistedEntity = null;
        if (repository != null) {
            persistedEntity = repository.save(entity);
        }
        return persistedEntity;
    }

    public Juridica findById(Long id) {
        if (repository != null) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    public Juridica findByCnpj(String cnpj) {
        if (repository != null) {
            return repository.findByCnpj(cnpj);
        }
        return null;
    }

    public void delete(Juridica entity) {
        if (repository != null) {
            repository.delete(entity);
        }
    }

    public void deleteById(Long id) {
        if (repository != null) {
            repository.deleteById(id);
        }
    }

    public Juridica update(Juridica entity) {
        Juridica persistedEntity = null;
        if (repository != null) {
            persistedEntity = repository.save(entity);
        }
        return persistedEntity;
    }

    public List<Juridica> findAll() {
        List<Juridica> list = null;
        if (repository != null) {
            list = repository.findAll();
        }
        return list != null ? list : new ArrayList<>();
    }
}
