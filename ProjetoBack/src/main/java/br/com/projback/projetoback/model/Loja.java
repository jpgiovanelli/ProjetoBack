package br.com.projback.projetoback.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class Loja {
    private String cnpj;
    private String nome;

    @DateTimeFormat
    private String data_cadastro;

    private String url;
    private int max_prod_page;
    private String aba_prod_add;

    private int id_loja;

    private int id_lojista;



}
