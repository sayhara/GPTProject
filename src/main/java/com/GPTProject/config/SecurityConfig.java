package com.GPTProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","sign-up").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        //js, css, image file ignore
        return (web -> web.ignoring().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

}
