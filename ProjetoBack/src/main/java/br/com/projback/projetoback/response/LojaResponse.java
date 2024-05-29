package br.com.projback.projetoback.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LojaResponse {
    private String cnpj;
    private String nome_loja;
    private LocalDateTime data_cadastro;
    private String url;
    private int max_prod_page;
    private String aba_prod_add;
    private Boolean enabled;
    private LocalDateTime dtAtivacao;
    private String userNameAtivacao;
    private int id;
}
