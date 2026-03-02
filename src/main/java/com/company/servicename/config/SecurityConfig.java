package com.company.servicename.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // SCAFFOLD — replace with real rules
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // SCAFFOLD — replace with real rules
                // SCAFFOLD — replace with real rules
                .csrf(AbstractHttpConfigurer::disable) // SCAFFOLD — replace with real rules
                // SCAFFOLD — replace with real rules
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // SCAFFOLD
                                                                                                              // —
                                                                                                              // replace
                                                                                                              // with
                                                                                                              // real
                                                                                                              // rules
                // SCAFFOLD — replace with real rules
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // SCAFFOLD — replace with real rules

        return http.build(); // SCAFFOLD — replace with real rules
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // SCAFFOLD — restrict origins in production
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
