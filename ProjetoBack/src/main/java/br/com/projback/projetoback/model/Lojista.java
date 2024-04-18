package br.com.projback.projetoback.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Lojista {
    private int id_lojista;
    private String nome_completo;
    private String email;
    private String telefone;

    @DateTimeFormat
    private String data_cadastro;

    private String cpf;



}
