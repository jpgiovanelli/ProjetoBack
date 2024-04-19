package br.com.projback.projetoback.controller;


import br.com.projback.projetoback.model.DadoBancario;
import br.com.projback.projetoback.model.Endereco;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.model.Lojista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro/lojista")
public class CadastroLojistaController {

    @Autowired
    private DadoBancario dados_bancarios;
    @Autowired
    private Endereco endereco;
    @Autowired
    private Loja loja;
    @Autowired
    private Lojista lojista;

    @GetMapping('/create')
    public


}
