package br.unesp.rc.sistemacadastro.entity.mapper;

import br.unesp.rc.sistemacadastro.entity.Acesso;
import br.unesp.rc.sistemacadastro.entity.Contato;
import br.unesp.rc.sistemacadastro.entity.Endereco;
import br.unesp.rc.sistemacadastro.entity.Juridica;

/**
* @author Prof. Dr. Frank J. Affonso
*/
public class JuridicaMapper {

    private JuridicaMapper() {
    }

    public static void update(Juridica juridicaUpdate, Juridica newJuridica) {
        juridicaUpdate.setCnpj(newJuridica.getCnpj());
        juridicaUpdate.setNome(newJuridica.getNome());

        if (juridicaUpdate.getAcesso() != null && newJuridica.getAcesso() != null) {
            Acesso a = juridicaUpdate.getAcesso();
            a.setUsuario(newJuridica.getAcesso().getUsuario());
            a.setSenha(newJuridica.getAcesso().getSenha());
            juridicaUpdate.setAcesso(a);
        } else if (newJuridica.getAcesso() != null) {
            juridicaUpdate.setAcesso(newJuridica.getAcesso());
        }

        if (juridicaUpdate.getContato() != null && newJuridica.getContato() != null) {
            Contato c = juridicaUpdate.getContato();
            c.setTelefoneResidencial(newJuridica.getContato().getTelefoneResidencial());
            c.setTelefoneComercial(newJuridica.getContato().getTelefoneComercial());
            c.setCelular(newJuridica.getContato().getCelular());
            c.setEmail(newJuridica.getContato().getEmail());
            juridicaUpdate.setContato(c);
        } else if (newJuridica.getContato() != null) {
            juridicaUpdate.setContato(newJuridica.getContato());
        }

        if (juridicaUpdate.getEndereco() != null && newJuridica.getEndereco() != null) {
            int i = 0;
            for (Endereco e : juridicaUpdate.getEndereco()) {
                if (i < newJuridica.getEndereco().size()) {
                    Endereco edto = newJuridica.getEndereco().get(i);
                    e.setRua(edto.getRua());
                    e.setNumero(edto.getNumero());
                    e.setBairro(edto.getBairro());
                    e.setCep(edto.getCep());
                    e.setCidade(edto.getCidade());
                    e.setEstado(edto.getEstado());
                    i++;
                }
            }
        }
    }
}
