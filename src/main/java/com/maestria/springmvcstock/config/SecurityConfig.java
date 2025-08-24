package com.maestria.springmvcstock.config;

import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      // CORS: ideal manejarlo en el Gateway; deja comentado en el MS
      // .cors(cors -> cors.configurationSource(corsConfigurationSource()))
      .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/actuator/**").permitAll()
        // ajusta qué proteger; ej. todo /api/v1/** autenticado:
        .requestMatchers("/api/v1/**").authenticated()
        // si quieres dejar GET públicos por ahora:
        // .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
        .anyRequest().authenticated()
      )
      .oauth2ResourceServer(o -> o.jwt()); // valida JWT del header Authorization

    return http.build();
  }

  @Bean
  JwtDecoder jwtDecoder(@Value("${security.jwt.secret}") String base64) {
    var key = new SecretKeySpec(
        io.jsonwebtoken.io.Decoders.BASE64.decode(base64),
        "HmacSHA256");
    var decoder = NimbusJwtDecoder.withSecretKey(key).build();

    // (Opcional) valida issuer además de la firma/exp:
    // var validator = org.springframework.security.oauth2.jwt.JwtValidators
    //       .createDefaultWithIssuer("http://localhost:8081");
    // decoder.setJwtValidator(validator);

    return decoder;
  }

  // Si realmente necesitas CORS aquí (recomendado solo en GW), descomenta y usa esto:
  // @Bean
  // CorsConfigurationSource corsConfigurationSource() {
  //   var cfg = new org.springframework.web.cors.CorsConfiguration();
  //   cfg.setAllowedOriginPatterns(java.util.List.of("http://localhost:5173"));
  //   cfg.setAllowedMethods(java.util.List.of("GET","POST","PUT","DELETE","OPTIONS"));
  //   cfg.setAllowedHeaders(java.util.List.of("*"));
  //   cfg.setAllowCredentials(true);
  //   var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
  //   source.registerCorsConfiguration("/**", cfg);
  //   return source;
  // }
}