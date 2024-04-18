package br.com.projback.projetoback.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DadoBancario {
    private int banco_id;
    private String codigoBanco;
    private String agencia;
    private String conta;
    private TipoConta tipoConta = TipoConta.CONTA_CORRENTE;

}
