package com.blogpessoal.blog_pessoal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogpessoal.blog_pessoal.security.JWTAuthenticationFilter;
import com.blogpessoal.blog_pessoal.security.JWTAuthorizationFilter;
import com.blogpessoal.blog_pessoal.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // Desabilita CSRF
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll() // Libera endpoints de autenticação
        .anyRequest().authenticated()
        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManagerBean())) // Filtro de login
        .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class) // Filtro de token
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Desabilita sessões
}
}
