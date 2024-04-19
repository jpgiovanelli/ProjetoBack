package br.com.projback.projetoback.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DadoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int banco_id;

    @Column(name = "cod_banco")
    private String codigoBanco;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "conta")
    private String conta;

    @Column(name = "tipo_conta")
    private TipoConta tipoConta;

}
