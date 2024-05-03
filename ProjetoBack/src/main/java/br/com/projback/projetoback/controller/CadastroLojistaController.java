package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.model.DadoBancario;
import br.com.projback.projetoback.model.Endereco;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.model.Lojista;
import br.com.projback.projetoback.repository.DadoBancario_Repository;
import br.com.projback.projetoback.repository.Endereco_Repository;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.repository.Lojista_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro/lojista")
public class CadastroLojistaController {

    @Autowired
    private DadoBancario_Repository dadoBancarioRepository;
    @Autowired
    private Endereco_Repository enderecoRepository;
    @Autowired
    private Loja_Repository lojaRepository;
    @Autowired
    private Lojista_Repository lojistaRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> createLojista(@RequestBody @Valid CadastroLojistaRequest request) throws Exception {

        Lojista lojista = Lojista.fromRequest(request);

        Loja loja = lojista.getLojas().getFirst();
        Endereco endereco = loja.getEndereco().getFirst();
        DadoBancario dadoBancario = lojista.getDado_bancario().getFirst();

        dadoBancarioRepository.save(dadoBancario);
        enderecoRepository.save(endereco);
        lojaRepository.save(loja);
        lojistaRepository.save(lojista);

        System.out.println(lojista);
        System.out.println("---------------------------");

        return new ResponseEntity(HttpStatus.OK);
    }


}
