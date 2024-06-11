package br.com.projback.projetoback.security.config;

import br.com.projback.projetoback.security.authentication.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/user/create",
            "/user/login"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/user",
            "/lojista",
            "/logista/get",
            "/lojista/update",
            "/lojista/change-status"
    };

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                .csrf((opt)-> opt.disable())
                .sessionManagement((opt)->opt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((opt)->{
                    opt.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                            .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                            .anyRequest().denyAll();
                })
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}