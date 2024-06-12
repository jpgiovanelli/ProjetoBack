package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.model.*;
import br.com.projback.projetoback.service.LojistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CadastroLojistaControllerTest {

    @MockBean
    private LojistaService lojistaService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private Lojista lojista;

    @BeforeEach
    public void setup() {
        lojista = new Lojista();

        lojista.setNome_completo("balla alala");
        lojista.setEmail("TESTE@GMAIL.COM");
        lojista.setTelefone("(21)11111-1111");
        lojista.setCpf("111.111.111-11");

        DadoBancario dadoBancario = new DadoBancario();

        dadoBancario.setTipoConta(TipoConta.CONTA_INVESTIMENTO);

        dadoBancario.setConta("999999");
        dadoBancario.setAgencia("9999");
        dadoBancario.setCodigoBanco("341");
        lojista.getDado_bancario().add(dadoBancario);

        Loja loja = new Loja();
        loja.setCnpj("12.345.678/9001-90");
        loja.setNome_loja("Testes Lojas");
        loja.setUrl("http://qqcoisa.com");
        loja.setMax_prod_page(3);
        loja.setAba_prod_add("Teste");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("sdfadfasfda");
        endereco.setComplemento("apt 000");
        endereco.setCep("12345-678");
        endereco.setBairro("Bairro Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setPais("brazuka");
        endereco.setEstado("rj");

        endereco.setTipo_endereco(TipoEndereco.RESIDENCIAL);

        loja.getEndereco().add(endereco);
        lojista.getLojas().add(loja);

        this.mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();

    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void deveConsultarLojistaPorIdComSucesso() throws Exception {
        int id = 0;
        given(this.lojistaService.getLojistaById(id)).willReturn(Lojista.toResponse(this.lojista));

        mvc.perform(MockMvcRequestBuilders.get("/lojista/get/byId/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.nome_completo", is(this.lojista.getNome_completo())));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void deveConsultarLojistaPorIdRetornandoNotFound() throws Exception {
        int id = -1;
        given(this.lojistaService.getLojistaById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/lojista/get/byId/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

