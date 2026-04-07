package co.istad.chhaya.webmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public DaoAuthenticationProvider daoAuthProvider(UserDetailsService userDetailsService,
                                                     PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }


    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) {
        // TODO:
        // 1. Security on endpoints
        http.authorizeHttpRequests(endpoint -> endpoint
                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/products/**").hasAnyRole("ADMIN", "BUSINESS")
                .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasAnyRole("ADMIN", "BUSINESS")
                .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasAnyRole("ADMIN", "BUSINESS")
                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                .anyRequest().authenticated()
        );

        // 2. Configure Security Mechanism -> HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // 3. Disable CSRF token
        http.csrf(token -> token.disable());

        // 4. Configure stateless
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );


        return http.build();
    }

}
