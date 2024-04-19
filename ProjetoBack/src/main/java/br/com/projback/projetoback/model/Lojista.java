package br.com.projback.projetoback.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Lojista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lojista;
    @Column
    private String nome_completo;
    @Column
    private String email;
    @Column
    private String telefone;

    @Column
    private LocalDateTime data_cadastro;

    @Column
    private String cpf;

    @OneToMany
    @JoinColumn(name = "id_lojista", referencedColumnName = "id_lojista")
    private List<DadoBancario> dado_bancario;

    @OneToMany
    @JoinColumn(name = "id_lojista", referencedColumnName = "id_lojista")
    private List<Loja> lojas;




}
