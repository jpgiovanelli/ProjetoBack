package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.DadoBancario;
import br.com.projback.projetoback.model.Endereco;
import br.com.projback.projetoback.model.Loja;
import br.com.projback.projetoback.model.Lojista;
import br.com.projback.projetoback.repository.DadoBancario_Repository;
import br.com.projback.projetoback.repository.Endereco_Repository;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.repository.Lojista_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.response.LojaResponse;
import br.com.projback.projetoback.response.LojistaResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/lojista")
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
    public ResponseEntity<LojistaResponse> createLojista(@RequestBody @Valid CadastroLojistaRequest request) throws Exception {

        Lojista lojista = Lojista.fromRequest(request);

        if (this.lojaRepository.findByCnpj(lojista.getLojas().getFirst().getCnpj()).isEmpty() == false) {
            throw new LojistaException("cpnj", "CNPJ da loja já cadastrado.");
        }

        if (this.lojistaRepository.findByCpf(lojista.getCpf()).isEmpty() == false) {
            throw new LojistaException("cpf", "CPF do lojista já cadastrado.");
        }

        Loja loja = lojista.getLojas().getFirst();
        Endereco endereco = loja.getEndereco().getFirst();
        DadoBancario dadoBancario = lojista.getDado_bancario().getFirst();

        dadoBancarioRepository.save(dadoBancario);
        enderecoRepository.save(endereco);
        lojaRepository.save(loja);
        lojistaRepository.save(lojista);

        LojistaResponse response = Lojista.toResponse(lojista);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/byId/{id}")
    public ResponseEntity<LojistaResponse> getLojistaById(@PathVariable int id) throws LojistaException {
        Optional<Lojista> optLojista = lojistaRepository.findById(id);

        if (optLojista.isEmpty())
            throw new LojistaException("id", "Lojista não encontrado.");

        return new ResponseEntity<>(Lojista.toResponse(optLojista.get()), HttpStatus.OK);
    }

    @GetMapping("get/byCnpj/")
    public ResponseEntity<LojaResponse> getLojistaByCnpj(@RequestParam String cnpj) throws LojistaException {
        Optional<Loja> optLoja = lojaRepository.findByCnpj(cnpj);

        if (optLoja.isEmpty())
            throw new LojistaException("id", "Lojista não encontrado.");

        return new ResponseEntity<>(Lojista.toResponse(optLoja.get()), HttpStatus.OK);

    }

    @GetMapping("get/byCpf/")
    public ResponseEntity<LojistaResponse> getLojistaByCpf(@RequestParam String cpf) throws LojistaException {
        Optional<Lojista> optLojista = lojistaRepository.findByCpf(cpf);

        if (optLojista.isEmpty())
            throw new LojistaException("cpf", "Lojista não encontrado.");

        return new ResponseEntity<>(Lojista.toResponse(optLojista.get()), HttpStatus.OK);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<LojistaResponse> updateLojista(@PathVariable int id, @RequestBody @Valid CadastroLojistaRequest request) throws LojistaException {
        Optional<Lojista> optLojista = lojistaRepository.findById(id);

        if (optLojista.isEmpty())
            throw new LojistaException("id", "Lojista não encontrado.");

        Lojista lojista = Lojista.fromRequest(optLojista.get(), request);

        Loja loja = lojista.getLojas().getFirst();
        Endereco endereco = loja.getEndereco().getFirst();
        DadoBancario dadoBancario = lojista.getDado_bancario().getFirst();

        dadoBancarioRepository.save(dadoBancario);
        enderecoRepository.save(endereco);
        lojaRepository.save(loja);
        lojistaRepository.save(lojista);

        LojistaResponse response = Lojista.toResponse(lojista);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
