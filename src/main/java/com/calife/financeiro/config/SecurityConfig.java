package com.calife.financeiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/esqueci-senha", "/resetar-senha", "/css/**", "/js/**",
                        "/image/**", "/webjars/**").permitAll()
                .requestMatchers("/*.html").denyAll() // Deny direct access to .html files
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Redirect to your login page
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
