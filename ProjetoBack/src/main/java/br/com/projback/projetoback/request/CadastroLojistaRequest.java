package br.com.projback.projetoback.request;

import br.com.projback.projetoback.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^\\b\\d{5}[-.]\\d{3}$", message = "Campo CEP está errado")
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
    @Pattern(regexp = "^\\d{2}.\\d{3}.\\d{3}\\/\\d{4}-\\d{2}$",message = "Campo CNPJ está incorreto")
    @NotBlank(message="Nao pode estar em branco")
    private String cnpj;

    @NotBlank(message="Nao pode estar em branco")
    private String nome_loja;

    @NotBlank(message="Nao pode estar em branco")
    private String url;

    private int max_prod_page;
    private String aba_prod_add;

    // Banco
    @Pattern(regexp = "^\\d{3}\\d{1}$",message = "Codigo do banco invalido")
    @NotBlank(message="Nao pode estar em branco")
    private String codigoBanco;

    @Pattern(regexp = "^\\d{1,5}$",message = "Campo agencia invalido")
    @NotBlank(message="Nao pode estar em branco")
    private String agencia;

    @Pattern(regexp = "^\\d{1,12}$",message = "Campo conta invalido")
    @NotBlank(message="Nao pode estar em branco")
    private String conta;

    @NotNull(message="Nao pode estar em branco")
    private String tipoConta;

    // Lojista
    @NotBlank(message="Nao pode estar em branco")
    private String nome_completo;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Campo email invalido")
    @NotBlank(message="Nao pode estar em branco")
    private String email;

    @Pattern(regexp = "^\\(?\\d{2}\\)?[-.\\s]?\\d{4,5}[-.\\s]?\\d{4}$",message ="Campo telefone invalido")
    @NotBlank(message="Nao pode estar em branco")
    private String telefone;

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",message = "Campo CPF invalido")
    @NotBlank(message="Nao pode estar em branco")
    private String cpf;

}