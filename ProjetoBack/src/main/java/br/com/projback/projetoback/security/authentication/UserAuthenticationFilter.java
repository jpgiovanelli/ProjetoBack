package br.com.projback.projetoback.security.authentication;

import br.com.projback.projetoback.model.User;
import br.com.projback.projetoback.repository.UserRepository;
import br.com.projback.projetoback.security.config.SecurityConfiguration;
import br.com.projback.projetoback.security.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Arrays;
import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Verifica se o endpoint requer autenticação
        if(checkIfIsRequired(request)){
            String token = getToken(request);
            if (token == null){
                throw new RuntimeException("O token está ausente");
            }
            String subject = jwtTokenService.getSubjectFromToken(token);
            User user = userRepository.findbyUsername(subject).get();
            UserDetailsImpl userDetails =  new UserDetailsImpl(user);

            //Cria o objeto autenticado
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token !=null){
            return token.replace("Bearer", "");
        }
        return null;
    }


    private boolean checkIfIsRequired(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        boolean isRequired = true;
        for(String publicUrl:SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED){
            if(publicUrl.contains(requestUrl)){
                isRequired = false;
            }
        }
        return isRequired;
    }
}
