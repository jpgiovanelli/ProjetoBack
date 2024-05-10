package br.com.projback.projetoback.service;

import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class LojaServiceTeste {
    @MockBean
    private Loja_Repository lojaRepository;

    @Test
    public void DeveCriarUmaLojaComSucesso(){
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
        request.setTipoConta("1");
        request.setTelefone("(21)11111-1111");
        request.setTipo_endereco("fasda");
        request.setMax_prod_page(3);
        request.setNome_completo("balla alala");
        request.setPais("brazuka");
        request.setLogradouro("sdfadfasfda");
        request.setCpf("111.111.111-11");
        request.setEstado("rj");
        request.setComplemento("apt 000");

        LojistaResponse response = this.service.criaLoja(request);

        Assertions.assertNotNull(request);
        Assertions.assertEquals(response.getNome_Completo, response.getNome_Completo);
        Assertions.assertNotNull(response.getID);
    }
}
