package br.unesp.rc.sistemacadastro.service;

import br.unesp.rc.sistemacadastro.entity.Produto;
import br.unesp.rc.sistemacadastro.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Prof. Dr. Frank J. Affonso
*/
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoService() {
    }

    public Produto save(Produto entity) {
        if (entity != null) {
            entity.atualizarStatus();
            if (repository != null) {
                return repository.save(entity);
            }
        }
        return null;
    }

    public Produto findById(Long id) {
        if (repository != null && id != null) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    public List<Produto> findAll() {
        if (repository != null) {
            List<Produto> list = repository.findAll();
            for (Produto p : list) {
                p.atualizarStatus();
            }
            return list;
        }
        return new ArrayList<>();
    }

    public Produto update(Produto entity) {
        if (entity != null) {
            entity.atualizarStatus();
            if (repository != null) {
                return repository.save(entity);
            }
        }
        return null;
    }

    public void delete(Produto entity) {
        if (repository != null && entity != null) {
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
}
