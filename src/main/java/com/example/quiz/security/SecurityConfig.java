package com.example.quiz.security;

import com.example.quiz.repo.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod; 

@Configuration
public class SecurityConfig {
  @Bean BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, JwtService jwt, UserRepo users) throws Exception {
    http.csrf(csrf -> csrf.disable());
    http.cors(Customizer.withDefaults());
    http.authorizeHttpRequests(auth -> auth
      .requestMatchers("/ping", "/actuator/health").permitAll()
      .requestMatchers(HttpMethod.GET, "/api/**").permitAll() // <-- let GETs through
      .anyRequest().authenticated()
    );
    http.addFilterBefore(new JwtAuthFilter(jwt, users), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  
}
