package com.rest_api.fs14backend.security;

import com.rest_api.fs14backend.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {
  @Autowired
  private JwtFilter jwtFilter;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/signup", "/api/v1/signin", "/api/v1/books/**", "/api/v1/users/me")
            .permitAll()
            .requestMatchers(antMatcher(HttpMethod.GET, "/api/v1/books/**"))
            .permitAll()
            .requestMatchers("/api/v1/authors/**", "/api/v1/categories/**", "/api/v1/roles/**", "/api/v1/users/**",
                    "/api/v1/books/borrow/**", "/api/v1/books/return/**", "/api/v1/books/book-copy",
                    "/api/v1/books/checkouts/**")
            .hasRole("ADMIN")
            .requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/books/**"),
                    antMatcher(HttpMethod.PUT, "/api/v1/books/**"), antMatcher(HttpMethod.DELETE, "/api/v1/books/**"))
            .hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
//            .httpBasic(withDefaults())
//            .formLogin()
//            .and()
            // Add JWT token filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}