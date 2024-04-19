package br.com.projback.projetoback.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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
    @Column
    private TipoEndereco tipo_endereco;


}
