package br.com.projback.projetoback.service;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.Lojista;
import br.com.projback.projetoback.repository.Loja_Repository;
import br.com.projback.projetoback.repository.Lojista_Repository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.response.LojistaResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class LojistaServiceTest {
    @Autowired
    private LojistaService service;

    @MockBean
    private Lojista_Repository lojistaRepository;

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
    public void DeveCriarUmaLojaComSucesso() throws Exception{
        //AAA(ARRANGE, ACT, ASSERT)

        given(this.lojistaRepository.findByCpf(request.getCpf())).willReturn(Optional.empty());

        LojistaResponse response = this.service.createLojista(request);

        Assertions.assertNotNull(request);
        Assertions.assertEquals(request.getNome_completo(), response.getNome_completo());
        Assertions.assertFalse(response.getEnabled());

    }

    @Test
    public void DeveCriarUmaLojaComSucesso2() throws Exception{
        //AAA(ARRANGE, ACT, ASSERT)

        given(this.lojistaRepository.findByCpf(request.getCpf())).willReturn(Optional.empty());

        request.setTipoConta("CC");
        request.setTipo_endereco("LOJA");
        request.setCnpj("12.345.678/9111-90");
        request.setCpf("111.111.111-22");

        LojistaResponse response = this.service.createLojista(request);

        Assertions.assertNotNull(request);
        Assertions.assertEquals(request.getNome_completo(), response.getNome_completo());
        Assertions.assertFalse(response.getEnabled());

    }

    @Test
    public void DeveCriarUmaLojaComSucesso3() throws Exception{
        //AAA(ARRANGE, ACT, ASSERT)

        given(this.lojistaRepository.findByCpf(request.getCpf())).willReturn(Optional.empty());

        request.setTipoConta("CP");
        request.setCnpj("12.345.678/9145-90");
        request.setCpf("111.111.111-33");

        LojistaResponse response = this.service.createLojista(request);

        Assertions.assertNotNull(request);
        Assertions.assertEquals(request.getNome_completo(), response.getNome_completo());
        Assertions.assertFalse(response.getEnabled());

    }

    @Test
    public void naoDeveCriarLojistaComCpfJaExistente() {
        given(this.lojistaRepository.findByCpf(request.getCpf())).willReturn(Optional.of(new Lojista()));

        LojistaResponse response;

        try {
            response = this.service.createLojista(request);
        } catch (Exception e) {
            response = null;
        }

        Assertions.assertNull(response);
    }

    @Test
    public void deveBuscarLojistaPorCPFComSucesso() throws Exception{
        String cpf = request.getCpf();
        given(this.lojistaRepository.findByCpf(cpf)).willReturn(Optional.of(new Lojista()));
        LojistaResponse response = this.service.getLojistaByCpf(cpf);

        Assertions.assertNotNull(response);
    }

    @Test
    public void NaoDeveRetornarLojistaPorCPFQuandoNaoEncontrarNaBase() throws LojistaException {
        String cpf = request.getCpf();
        given(this.lojistaRepository.findByCpf(cpf))
                .willReturn(Optional.empty());

        LojistaResponse response;

        try {
            response = this.service.getLojistaByCpf(cpf);
        } catch (LojistaException e) {
            response = null;
        }

        Assertions.assertNull(response);
    }

    @Test
    public void DeveAtualizarLojistaPorIDComSucesso() throws Exception{
        int id = 1;
        given(this.lojistaRepository.findById(id)).willReturn(Optional.of(new Lojista()));
        LojistaResponse response = this.service.updateLojista(id, request);

        Assertions.assertNotNull(response);
    }

    @Test
    public void NaoDeveAtualizarLojistaPorIDQuandoNaoEncontrarNaBase() throws LojistaException {
        int id = 1;
        given(this.lojistaRepository.findById(id))
                .willReturn(Optional.empty());

        LojistaResponse response;

        try {
            response = this.service.updateLojista(id, request);
        } catch (LojistaException e) {
            response = null;
        }
        Assertions.assertNull(response);
    }

    @Test
    public void DeveBuscarLojistaPorIDComSucesso() throws Exception{
        int id = 1;
        given(this.lojistaRepository.findById(id)).willReturn(Optional.of(new Lojista()));
        LojistaResponse response = this.service.getLojistaById(id);

        Assertions.assertNotNull(response);
    }

    @Test
    public void NaoDeveObterLojistaPorIdQuandoNaoEncontrarNaBase() {
        int id = 1;
        given(this.lojistaRepository.findById(id))
                .willReturn(Optional.empty());
        LojistaResponse response = this.service.getLojistaById(id);
        Assertions.assertNull(response);
    }



}
