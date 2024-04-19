package br.com.projback.projetoback.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int endereco_id;

    @Column
    private String logradouro;
    @Column
    private String complemento;
    @Column
    private String cep;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String pais;
    @Column
    private String estado;

    private TipoEndereco endereco;


    private int id_loja;
    private int id_lojista;


}
