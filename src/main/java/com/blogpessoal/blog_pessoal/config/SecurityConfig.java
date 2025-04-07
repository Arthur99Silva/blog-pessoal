package com.blogpessoal.blog_pessoal.config;

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

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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
        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/auth/login");

        http.csrf().disable()
            .authorizeRequests()
                .antMatchers(
                    "/auth/**",                   // libera login
                    "/swagger-ui/**",              // libera swagger-ui
                    "/swagger-ui.html",            // libera swagger-ui.html
                    "/v3/api-docs/**",              // libera documentação da api
                    "/v3/api-docs",
                    "/swagger-resources/**",        // libera recursos do swagger
                    "/webjars/**",                  // libera js/css estático
                    "/api-docs/**",                 // libera api-docs
                    "/swagger-ui/index.html"        // libera o index do swagger-ui
                ).permitAll()
                .anyRequest().authenticated()
            .and()
            .addFilter(authenticationFilter)
            .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
