package br.com.projback.projetoback.controller;

import br.com.projback.projetoback.model.*;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.request.UserRequest;
import br.com.projback.projetoback.service.LojaService;
import br.com.projback.projetoback.service.LojistaService;
import br.com.projback.projetoback.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();

        user.setUsername("teste");
        user.setPassword("teste");

        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "teste", password = "teste", roles = "USER")
    public void deveAcharUserById() throws Exception {
        int id = 1;
        given(this.userService.getById(id)).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/user/get/byId/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user.getUsername())));
    }

    @Test
    @WithMockUser(username = "teste", password = "teste", roles = "USER")
    public void deveAcharUserByIdRetornandoNotFound() throws Exception {
        int id = 1;
        given(this.userService.getById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/user/get/byId/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveCriarUserComSucesso() throws Exception {
        UserRequest request = new UserRequest();
        request.setUsername("teste");
        request.setPassword("teste");
        request.setProfile_id(1);

        given(this.userService.create(request.getUsername(), request.getPassword(), request.getProfile_id())).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(user.getUsername())));
    }

    @Test
    @WithMockUser(username = "teste", password = "teste", roles = "USER")
    public void deveObterTodosOsUsuarios() throws Exception {
        given(this.userService.getAll()).willReturn(List.of(user));

        mvc.perform(MockMvcRequestBuilders.get("/user/get/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is(user.getUsername())));
    }

    @Test
    public void deveFazerLogin() throws Exception {
        UserRequest request = new UserRequest();
        request.setUsername("teste");
        request.setPassword("teste");

        given(this.userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword())).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void deveFazerLoginRetornandoNotFound() throws Exception {
        UserRequest request = new UserRequest();
        request.setUsername("teste");
        request.setPassword("teste");

        given(this.userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword())).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

}

