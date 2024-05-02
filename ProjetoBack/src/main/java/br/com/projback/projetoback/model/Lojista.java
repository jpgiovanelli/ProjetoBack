package br.com.projback.projetoback.model;

import br.com.projback.projetoback.request.CadastroLojistaRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Lojista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lojista;
    @Column
    private String nome_completo;
    @Column
    private String email;
    @Column
    private String telefone;

    @Column
    private LocalDateTime data_cadastro;

    @Column
    private String cpf;

    @OneToMany
    @JoinColumn(name = "id_lojista", referencedColumnName = "id_lojista")
    private List<DadoBancario> dado_bancario;

    @OneToMany
    @JoinColumn(name = "id_lojista", referencedColumnName = "id_lojista")
    private List<Loja> lojas;

    public static Lojista fromRequest(CadastroLojistaRequest request) {
        Lojista lojista = new Lojista();

        lojista.setNome_completo(request.getNome_completo());
        lojista.setEmail(request.getEmail());
        lojista.setTelefone(request.getTelefone());
        lojista.setCpf(request.getCpf());
        lojista.setData_cadastro(request.getData_cadastro());

        DadoBancario dadobancario = new DadoBancario();
        dadobancario.setAgencia(request.getAgencia());
        dadobancario.setConta(request.getConta());

        if (request.getTipoConta().equals("CC")) {
            dadobancario.setTipoConta(TipoConta.CONTA_CORRENTE);
        } else if (request.getTipoConta().equals("CP")) {
            dadobancario.setTipoConta(TipoConta.CONTA_POUPANCA);
        } else if (request.getTipoConta().equals("CI")) {
            dadobancario.setTipoConta(TipoConta.CONTA_INVESTIMENTO);
        }

        lojista.getDado_bancario().add(dadobancario);

        Loja loja = new Loja();
        loja.setCnpj(request.getCnpj());
        loja.setNome_loja(request.getNome_loja());
        loja.setData_cadastro(request.getData_cadastro());
        loja.setUrl(request.getUrl());
        loja.setMax_prod_page(request.getMax_prod_page());
        loja.setAba_prod_add(request.getAba_prod_add());

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getLogradouro());
        endereco.setComplemento(request.getComplemento());
        endereco.setCep(request.getCep());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setPais(request.getPais());
        endereco.setEstado(request.getEstado());

        if (request.getTipo_endereco().equals("LOJA")) {
            endereco.setTipo_endereco(TipoEndereco.LOJA);
        } else if (request.getTipo_endereco().equals("RESIDENCIAL")) {
            endereco.setTipo_endereco(TipoEndereco.RESIDENCIAL);
        }

        loja.getEndereco().add(endereco);
        lojista.getLojas().add(loja);

        return lojista;
    };
}
