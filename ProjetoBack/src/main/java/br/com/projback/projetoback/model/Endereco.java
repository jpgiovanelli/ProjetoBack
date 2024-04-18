package br.com.projback.projetoback.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Endereco {
    @Id
    private int endereco_id;

    private String logradouro;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String pais;
    private String estado;

    private TipoEndereco endereco;

    private int id_loja;
    private int id_lojista;



}
