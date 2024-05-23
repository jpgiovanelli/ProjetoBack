package br.com.projback.projetoback.response;

import br.com.projback.projetoback.model.TipoConta;
import br.com.projback.projetoback.model.TipoEndereco;
import lombok.Data;

@Data
public class  CadastroLojistaResponse {
    private int id;
    private String logradouro;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String pais;
    private String estado;
    private TipoEndereco tipo_endereco;
    private String cnpj;
    private String nome_loja;
    private String url;
    private int max_prod_page;
    private String aba_prod_add;
    private String codigoBanco;
    private String agencia;
    private String conta;
    private TipoConta tipoConta;
    private Boolean enabled;
    private String nome_completo;
    private String email;
    private String telefone;
    private String cpf;
}
