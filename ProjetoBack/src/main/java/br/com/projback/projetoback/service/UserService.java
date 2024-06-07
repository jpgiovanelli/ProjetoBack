package br.com.projback.projetoback.service;

import br.com.projback.projetoback.exception.LojistaException;
import br.com.projback.projetoback.model.Profile;
import br.com.projback.projetoback.model.User;
import br.com.projback.projetoback.repository.ProfileRepository;
import br.com.projback.projetoback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProfileRepository perfilRepository;

    public User create(String username, String password, int idPerfil) throws LojistaException {

        Optional<Profile> optRole = this.perfilRepository.findById(idPerfil);

        if (optRole.isEmpty())
            throw new LojistaException("perfil", "Perfil não encontrado");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.getProfiles().add(optRole.get());

        this.userRepository.save(user);

        return user;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(int id) {
        Optional<User> optuser = this.userRepository.findById(id);

        if (optuser.isEmpty())
            return null;

        return optuser.get();

    }

    public User getUserByUsernameAndPassword(String username, String password) {
        Optional<User> optuser = this.userRepository.findByUsernameAndPassword(username, password);

        if (optuser.isEmpty())
            return null;

        return optuser.get();
    }
}
