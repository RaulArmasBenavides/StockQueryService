package com.maestria.springmvcstock.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain security(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(a -> a.anyRequest().permitAll())
        .build();
  }

  // Si realmente necesitas CORS aquí (recomendado solo en GW), descomenta y usa
  // esto:
  // @Bean
  // CorsConfigurationSource corsConfigurationSource() {
  // var cfg = new org.springframework.web.cors.CorsConfiguration();
  // cfg.setAllowedOriginPatterns(java.util.List.of("http://localhost:5173"));
  // cfg.setAllowedMethods(java.util.List.of("GET","POST","PUT","DELETE","OPTIONS"));
  // cfg.setAllowedHeaders(java.util.List.of("*"));
  // cfg.setAllowCredentials(true);
  // var source = new
  // org.springframework.web.cors.UrlBasedCorsConfigurationSource();
  // source.registerCorsConfiguration("/**", cfg);
  // return source;
  // }
}