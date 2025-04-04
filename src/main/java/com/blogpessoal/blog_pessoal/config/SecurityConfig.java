package com.blogpessoal.blog_pessoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogpessoal.blog_pessoal.security.JWTAuthenticationFilter;
import com.blogpessoal.blog_pessoal.security.JWTAuthorizationFilter;
import com.blogpessoal.blog_pessoal.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Criação do PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração do AuthenticationManager
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Configuração das permissões e filtros de segurança
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()  // Permite o acesso às rotas de login sem autenticação
            .anyRequest().authenticated()  // Requer autenticação para qualquer outra rota
            .and()
            .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class) // Filtro de autorização JWT
            .addFilter(new JWTAuthenticationFilter(authenticationManagerBean())); // Filtro de autenticação JWT
    }

    // Configuração do AuthenticationManagerBuilder com BCryptPasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
