package com.company.LibraryProject.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
public class ApplicationConfiguration {

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value(value = "${spring.datasource.username}")
    private String username;

    @Value(value = "${spring.datasource.password}")
    private String password;


    @Bean(name = "first")
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource sdd = new SimpleDriverDataSource();
        sdd.setUrl(url);
        sdd.setUsername(username);
        sdd.setPassword(password);
        sdd.setDriverClass(org.postgresql.Driver.class);
        return sdd;
    }


    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();
    }

    @PostConstruct
    public void init() {
        System.out.println("This init method execute!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("This destroy method execute!");
    }

}
