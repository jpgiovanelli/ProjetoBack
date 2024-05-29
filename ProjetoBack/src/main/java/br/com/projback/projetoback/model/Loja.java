package br.com.projback.projetoback.model;

import br.com.projback.projetoback.response.LojaResponse;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "loja")
public class Loja {
    @Column
    private String cnpj;

    @Column
    private String nome_loja;

    @Column
    private LocalDateTime data_cadastro = LocalDateTime.now();

    @Column
    private String url;

    @Column
    private int max_prod_page;

    @Column
    private String aba_prod_add;

    @Column
    private Boolean enabled = false;

    @Column
    private LocalDateTime dtAtivacao;

    @Column
    private String userNameAtivacao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private List<Endereco> endereco = new ArrayList<>();

        public static LojaResponse toResponse(Loja loja) {

        LojaResponse response = new LojaResponse();
        response.setCnpj(loja.getCnpj());
        response.setNome_loja(loja.getNome_loja());
        response.setUrl(loja.getUrl());
        response.setMax_prod_page(loja.getMax_prod_page());
        response.setAba_prod_add(loja.getAba_prod_add());
        response.setData_cadastro(loja.getData_cadastro());
        response.setEnabled(loja.getEnabled());
        response.setId(loja.getId());
        response.setDtAtivacao(loja.getDtAtivacao());
        response.setUserNameAtivacao(loja.getUserNameAtivacao());

        return response;
    }

}
