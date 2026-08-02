package br.unesp.rc.sistemacadastro.dto.assembler;

import br.unesp.rc.sistemacadastro.dto.EnderecoDTO;
import br.unesp.rc.sistemacadastro.dto.JuridicaDTO;
import br.unesp.rc.sistemacadastro.entity.Acesso;
import br.unesp.rc.sistemacadastro.entity.Contato;
import br.unesp.rc.sistemacadastro.entity.Endereco;
import br.unesp.rc.sistemacadastro.entity.Juridica;

/**
* @author Prof. Dr. Frank J. Affonso
*/
public class JuridicaAssembler {

    private JuridicaAssembler() {
    }

    public static Juridica dtoToEntityModel(JuridicaDTO dto) {
        Juridica juridica = new Juridica();

        juridica.setCnpj(dto.getCnpj());
        juridica.setNome(dto.getNome());

        Acesso a = new Acesso();
        a.setUsuario(dto.getUsuario());
        a.setSenha(dto.getSenha());
        juridica.setAcesso(a);

        Contato c = new Contato();
        c.setTelefoneResidencial(dto.getTelefoneResidencial());
        c.setTelefoneComercial(dto.getTelefoneComercial());
        c.setCelular(dto.getCelular());
        c.setEmail(dto.getEmail());
        juridica.setContato(c);

        if (dto.getEndereco() != null) {
            for (EnderecoDTO edto : dto.getEndereco()) {
                Endereco e = new Endereco();
                e.setRua(edto.getRua());
                e.setNumero(edto.getNumero());
                e.setBairro(edto.getBairro());
                e.setCep(edto.getCep());
                e.setCidade(edto.getCidade());
                e.setEstado(edto.getEstado());
                juridica.setEndereco(e);
            }
        }

        return juridica;
    }
}
