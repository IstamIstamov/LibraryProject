package com.company.LibraryProject.config;

import com.company.LibraryProject.security.JwtFilter;
import com.company.LibraryProject.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

    /*@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityFilter filter;*/

    @Autowired
    private JwtFilter jwtFilter;

    /* @Autowired
     public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
         builder.inMemoryAuthentication()
                 .withUser("User")
                 .password(passwordEncoder.encode("root"))
                 .roles("ADMIN")
                 .and()
                 .withUser("Hasanboy")
                 .password(passwordEncoder.encode("root"))
                 .roles("ADMIN")
                 .and()
                 .passwordEncoder(passwordEncoder);
     }*/


    /*@Autowired
    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/sign-in", "/user/create", "/user/refresh", "/auth/**").permitAll()
                .anyRequest()
                .authenticated().and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
