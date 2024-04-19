package br.com.projback.projetoback.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Loja {
    @Column
    private String cnpj;

    @Column
    private String nome;

    @Column
    private LocalDateTime data_cadastro;

    @Column
    private String url;

    @Column
    private int max_prod_page;

    @Column
    private String aba_prod_add;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_loja;

    private int id_lojista;



}
