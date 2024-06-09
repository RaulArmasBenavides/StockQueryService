package com.maestria.springmvcstock.config;
import com.maestria.springmvcstock.config.JwtAuthenticationFilter;
import com.maestria.springmvcstock.config.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

   private final AuthenticationConfiguration authenticationConfiguration;

   private static final String[] PUBLIC_MATCHERS = {
    "/login",
    "/api/v1/register"
};
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // Permitir acceso anónimo a todas las solicitudes
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // .addFilter(new JwtAuthenticationFilter(authenticationManager))
        // .addFilterBefore(new JwtAuthorizationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }
}