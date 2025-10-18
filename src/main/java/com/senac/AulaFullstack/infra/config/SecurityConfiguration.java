package com.senac.AulaFullstack.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                // DESLIGA A SEGURANÇA
                                .requestMatchers("/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/swagger-resources/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/usuarios").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
