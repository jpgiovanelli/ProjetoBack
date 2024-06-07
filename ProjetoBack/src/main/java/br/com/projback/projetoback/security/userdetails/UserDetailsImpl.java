package br.com.projback.projetoback.security.userdetails;

import br.com.projback.projetoback.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private User user;

    public UserDetailsImpl(User user){
        this.user = user;

    }

    public UserDetailsImpl(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getProfiles()
                .stream()
                .map((profile -> new SimpleGrantedAuthority(profile.getName())))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
