package br.com.projback.projetoback.request;

import br.com.projback.projetoback.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CadastroLojistaRequest {

    // Endenreco
    @NotBlank(message="Nao pode estar em branco")
    private String logradouro;
    @NotBlank(message="Nao pode estar em branco")
    private String complemento;
    @NotBlank(message="Nao pode estar em branco")
    private String cep;
    @NotBlank(message="Nao pode estar em branco")
    private String bairro;
    @NotBlank(message="Nao pode estar em branco")
    private String cidade;
    @NotBlank(message="Nao pode estar em branco")
    private String pais;
    @NotBlank(message="Nao pode estar em branco")
    private String estado;

    @NotNull(message="Nao pode estar em branco")
    private String tipo_endereco;

    // Loja
    @NotBlank(message="Nao pode estar em branco")
    private String cnpj;
    @NotBlank(message="Nao pode estar em branco")
    private String nome_loja;

    private LocalDateTime data_cadastro = LocalDateTime.now();
    @NotBlank(message="Nao pode estar em branco")
    private String url;

    private int max_prod_page;
    private String aba_prod_add;

    // Banco
    @NotBlank(message="Nao pode estar em branco")
    private String codigoBanco;
    @NotBlank(message="Nao pode estar em branco")
    private String agencia;
    @NotBlank(message="Nao pode estar em branco")
    private String conta;
    @NotNull(message="Nao pode estar em branco")
    private String tipoConta;

    // Lojista
    @NotBlank(message="Nao pode estar em branco")
    private String nome_completo;
    @NotBlank(message="Nao pode estar em branco")
    private String email;
    @NotBlank(message="Nao pode estar em branco")
    private String telefone;
    @NotBlank(message="Nao pode estar em branco")
    private String cpf;

}