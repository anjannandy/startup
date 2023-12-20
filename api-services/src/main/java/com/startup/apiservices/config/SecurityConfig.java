package com.startup.apiservices.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
  public static final String USER = "USER";
  private final JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        (request) -> {
          request
              .requestMatchers(HttpMethod.GET, "/user", "/public")
              .permitAll()
              .requestMatchers(HttpMethod.GET, "/api")
              .hasAnyRole(USER)
              .anyRequest()
              .authenticated();
        });
    http.oauth2ResourceServer(
        (request) -> {
          request.jwt(
              (jwt) -> {
                jwt.jwtAuthenticationConverter(jwtAuthConverter);
              });
        });

    http.sessionManagement(
        (request) -> {
          request.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

    return http.build();
  }
}
