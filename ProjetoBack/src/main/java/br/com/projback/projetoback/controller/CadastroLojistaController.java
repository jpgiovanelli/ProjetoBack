package br.com.projback.projetoback.controller;
import br.com.projback.projetoback.model.DadoBancario;
import br.com.projback.projetoback.model.Endereco;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.model.Lojista;
import br.com.projback.projetoback.repository.DadoBancario_Repository;
import br.com.projback.projetoback.repository.Endereco_Repository;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.repository.Lojista_Repository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> createLojista(@RequestBody CadastroLojistaRequest request) {
    DadoBancario dadoBancario = request.getDadoBancario();
    Endereco endereco = request.getEndereco();
    Loja loja = request.getLoja();
    Lojista lojista = request.getLojista();

    System.out.println(dadoBancario);
    System.out.println(endereco);
    System.out.println(lojista);
    System.out.println(loja);
    System.out.println("---------------------------");

//    lojistaRepository.save(lojista);
//    lojaRepository.save(loja);
//    enderecoRepository.save(endereco);
//    dadoBancarioRepository.save(dadoBancario);

    return ResponseEntity.ok().build();
}



}
