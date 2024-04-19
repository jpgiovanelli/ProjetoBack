package br.com.projback.projetoback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DadoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int banco_id;

    private String codigoBanco;
    private String agencia;
    private String conta;
    private TipoConta tipoConta;

    private int id_lojista;

}
