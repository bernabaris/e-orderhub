package com.github.bernabaris.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Bean
    UserDetailsService userDetails() throws Exception{
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails details = User.withUsername(username).password(passwordEncoder().encode(password)).roles("").build();
        userDetailsService.createUser(details);
        return userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .maximumSessions(1)  // opsiyonel
                )
                .httpBasic(httpBasic -> {}) // veya tamamen çıkarabilirsin
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
