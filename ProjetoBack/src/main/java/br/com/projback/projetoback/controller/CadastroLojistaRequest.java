package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.model.DadoBancario;
import br.com.projback.projetoback.model.Endereco;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.model.Lojista;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CadastroLojistaRequest {
    private DadoBancario dadoBancario;
    private Endereco endereco;
    private Loja loja;
    private Lojista lojista;

}