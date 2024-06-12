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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Arrays;
import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Verifica se o endpoint requer autenticação
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String token = obterToken(request);

            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);
                User user = usuarioRepository.findByUsername(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                //Cria o objeto de autenticação
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Define o objeto autenticado dentro do Spring
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        filterChain.doFilter(request, response);
    }

    private String obterToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer ", "");
        }
        return null;
    }
}
