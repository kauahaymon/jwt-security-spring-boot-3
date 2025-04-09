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
                 .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(WHITE_LIST).permitAll();
                    auth.requestMatchers(USER_ENDPOINTS).hasAnyRole("USER", "ADMIN");
                    auth.requestMatchers(ADMIN_ENDPOINTS).hasAnyRole("ADMIN");
                    auth.anyRequest().authenticated();
                 })
                 .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();
    }

    private static final String[] WHITE_LIST = {
            "/auth/**"
    };

    private static final String[] USER_ENDPOINTS = {
            "/test/users",
            "/products/**",
            "/orders/**"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/test/admins/**",
            "/admin/**"
    };
}
