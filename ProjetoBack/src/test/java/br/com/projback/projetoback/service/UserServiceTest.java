package br.com.projback.projetoback.service;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.Lojista;
import br.com.projback.projetoback.model.Profile;
import br.com.projback.projetoback.model.User;
import br.com.projback.projetoback.repository.Lojista_Repository;
import br.com.projback.projetoback.repository.ProfileRepository;
import br.com.projback.projetoback.repository.UserRepository;
import br.com.projback.projetoback.request.CadastroLojistaRequest;
import br.com.projback.projetoback.response.LojistaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService service;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProfileRepository perfilRepository;

    private User user;
    @BeforeEach
    public void Setup() {
        user = new User();
        user.setUsername("teste");
        user.setPassword("teste");
    }


    @Test
    public void deveAcharUserByUsernameAndPassword() {

        given(this.userRepository.findByUsernameAndPassword("teste", "teste")).willReturn(Optional.of(user));

        User userResponse = this.service.getUserByUsernameAndPassword("teste", "teste");

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername());
        Assertions.assertEquals(user.getPassword(), userResponse.getPassword());
    }

    @Test
    public void deveRetornarNullSeNaoAcharUserByUsernameAndPassword() {
        given(this.userRepository.findByUsernameAndPassword("teste", "teste")).willReturn(Optional.empty());

        User userResponse = this.service.getUserByUsernameAndPassword("teste", "teste");

        Assertions.assertNull(userResponse);
    }

    @Test
    public void DeveAcharUserById() {

        given(this.userRepository.findById(1)).willReturn(Optional.of(user));

        User userResponse = this.service.getById(1);

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername());
        Assertions.assertEquals(user.getPassword(), userResponse.getPassword());
    }

    @Test
    public void DeveRetornarNullSeNaoAcharUserById() {
        given(this.userRepository.findById(1)).willReturn(Optional.empty());

        User userResponse = this.service.getById(1);

        Assertions.assertNull(userResponse);
    }

    @Test
    public void deveListarTodosUsuarios() {

        given(this.userRepository.findAll()).willReturn(List.of(user));

        List<User> users = this.service.getAll();

        Assertions.assertNotNull(users);
        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void deveCriarUsuarioComUserNamePasswordEPerfil() throws LojistaException {
        Profile profile = new Profile();
        profile.setId(1);
        profile.setName("ADMIN");

        given(this.perfilRepository.findById(1)).willReturn(Optional.of(profile));

        User user = this.service.create("teste", "teste", 1);

        Assertions.assertNotNull(user);
        Assertions.assertEquals("teste", user.getUsername());
        Assertions.assertEquals("teste", user.getPassword());
        Assertions.assertEquals(1, user.getProfiles().size());
        Assertions.assertEquals("ADMIN", user.getProfiles().get(0).getName());
    }
}
