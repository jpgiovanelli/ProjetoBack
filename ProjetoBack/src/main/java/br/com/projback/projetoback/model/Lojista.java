package br.com.projback.projetoback.model;

import br.com.projback.projetoback.exception.LojaException;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "lojista")
public class Lojista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome_completo;
    @Column
    private String email;
    @Column
    private String telefone;

    @Column
    private LocalDateTime data_cadastro = LocalDateTime.now();

    @Column
    private String cpf;

    @OneToMany
    @JoinColumn(name = "id_lojista", referencedColumnName = "id")
    private List<DadoBancario> dado_bancario = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id_lojista", referencedColumnName = "id")
    private List<Loja> lojas = new ArrayList<>();

    public static Lojista fromRequest(CadastroLojistaRequest request) throws Exception {
        Lojista lojista = new Lojista();

        lojista.setNome_completo(request.getNome_completo());
        lojista.setEmail(request.getEmail());
        lojista.setTelefone(request.getTelefone());
        lojista.setCpf(request.getCpf());

        DadoBancario dadoBancario = new DadoBancario();

        if (request.getTipoConta().equals("CC")) {
            dadoBancario.setTipoConta(TipoConta.CONTA_CORRENTE);
        } else if (request.getTipoConta().equals("CP")) {
            dadoBancario.setTipoConta(TipoConta.CONTA_POUPANCA);
        } else if (request.getTipoConta().equals("CI")) {
            dadoBancario.setTipoConta(TipoConta.CONTA_INVESTIMENTO);
        } else {
            throw new LojaException("tipoConta", "Tipo de conta invalido, valores validos: CC, CI, CP");
        }

        dadoBancario.setConta(request.getConta());
        dadoBancario.setAgencia(request.getAgencia());
        dadoBancario.setCodigoBanco(request.getCodigoBanco());
        lojista.getDado_bancario().add(dadoBancario);

        Loja loja = new Loja();
        loja.setCnpj(request.getCnpj());
        loja.setNome_loja(request.getNome_loja());
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
        } else {
            throw new LojaException("tipo_endereco", "Tipo de ENDERECO invalido, valores validos: LOJA, RESIDENCIAL");
        }

        loja.getEndereco().add(endereco);
        lojista.getLojas().add(loja);

        return lojista;
    };
}
