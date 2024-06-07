package br.com.projback.projetoback.security.userdetails;

import br.com.projback.projetoback.model.User;
import br.com.projback.projetoback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findbyUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
        return new UserDetailsImpl(user);
    }


}
