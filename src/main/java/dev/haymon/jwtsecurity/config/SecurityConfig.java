package dev.haymon.jwtsecurity.config;

import dev.haymon.jwtsecurity.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .cors(withDefaults())
                 .csrf(AbstractHttpConfigurer::disable)
                 .httpBasic(withDefaults())
                 .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll();

                    auth.anyRequest().authenticated();
                 })
                 .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();
    }
}
