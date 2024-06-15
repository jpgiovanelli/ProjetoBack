package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.request.EnableLojaRequest;
import br.com.projback.projetoback.response.LojaResponse;
import br.com.projback.projetoback.response.LojistaResponse;
import br.com.projback.projetoback.service.LojaService;
import br.com.projback.projetoback.service.LojistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lojista")
public class CadastroLojistaController {

    @Autowired
    private LojistaService lojistaService;

    @Autowired
    private LojaService lojaService;

    @PostMapping("/create")
    public ResponseEntity<LojistaResponse> createLojista(@RequestBody @Valid CadastroLojistaRequest request) throws Exception {
        LojistaResponse response = lojistaService.createLojista(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/byId/{id}")
    public ResponseEntity<LojistaResponse> getLojistaById(@PathVariable int id) throws LojistaException {

        LojistaResponse response = lojistaService.getLojistaById(id);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/byCnpj/")
    public ResponseEntity<LojaResponse> getLojistaByCnpj(@RequestParam String cnpj) throws LojistaException {
        LojaResponse response = lojaService.getLojaByCnpj(cnpj);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lojaService.getLojaByCnpj(cnpj), HttpStatus.OK);
    }

    @GetMapping("get/byCpf/")
    public ResponseEntity<LojistaResponse> getLojistaByCpf(@RequestParam String cpf) throws LojistaException {
        LojistaResponse response = lojistaService.getLojistaByCpf(cpf);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<LojistaResponse> updateLojista(@PathVariable int id, @RequestBody @Valid CadastroLojistaRequest request) throws LojistaException {
        return new ResponseEntity<>(lojistaService.updateLojista(id, request), HttpStatus.OK);
    }

    @PatchMapping("change-status/{id}")
    public ResponseEntity<LojaResponse> changeStatusLoja(@PathVariable int id, @RequestBody @Valid EnableLojaRequest request) throws LojistaException {
        return new ResponseEntity<>(lojaService.changeStatusLoja(id, request), HttpStatus.OK);
    }

}
