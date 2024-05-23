package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.response.CadastroLojistaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@SpringBootTest
public class LojaControllerTest {
    @Autowired
    private CadastroLojistaController controller;

    @MockBean
    private Loja_Repository lojaRepository;

    @Test
    public void DeveCriarUmaLojaComSucesso() throws Exception{
        //AAA(ARRANGE, ACT, ASSERT)

        CadastroLojistaRequest request = new CadastroLojistaRequest();
        request.setCnpj("1111111111111");
        request.setCep("1111-111");
        request.setCidade("Nikiti");
        request.setBairro("itacoa");
        request.setAgencia("1111");
        request.setAba_prod_add("1");
        request.setNome_loja("aaa");
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

        given(this.lojaRepository.findByCnpj(request.getCnpj())).willReturn(Optional.empty());

        ResponseEntity<CadastroLojistaResponse> response = this.controller.createLojista(request);

        Assertions.assertNotNull(request);
        Assertions.assertEquals(request.getNome_completo(), response.getBody().getNome_completo());
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertFalse(response.getBody().getEnabled());
    }
}
