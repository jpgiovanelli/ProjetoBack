package br.com.projback.projetoback.service;

import br.com.projback.projetoback.controller.CadastroLojistaController;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.response.LojistaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@SpringBootTest
public class LojaServiceTest {

    @Autowired
    private LojaService service;

    @MockBean
    private Loja_Repository lojaRepository;

    private CadastroLojistaRequest request;
    @BeforeEach
    public void Setup() {
        request = new CadastroLojistaRequest();
        request.setCnpj("12.345.678/9001-90");
        request.setCep("12345-678");
        request.setCidade("Cidade Teste");
        request.setBairro("Bairro Teste");
        request.setAgencia("1111");
        request.setAba_prod_add("Teste");
        request.setNome_loja("Testes Lojas");
        request.setCodigoBanco("3333");
        request.setUrl("http://qqcoisa.com");
        request.setTipoConta("CI");
        request.setTelefone("(21)11111-1111");
        request.setTipo_endereco("RESIDENCIAL");
        request.setMax_prod_page(3);
        request.setNome_completo("balla alala");
        request.setPais("brazuka");
        request.setLogradouro("sdfadfasfda");
        request.setCpf("111.111.111-11");
        request.setEstado("rj");
        request.setComplemento("apt 000");
        request.setEmail("blabal@blabla.com");
    }

    @Test
    public void deveRetornarLojaCorreta() throws Exception {}


}

