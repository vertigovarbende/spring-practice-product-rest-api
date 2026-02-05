package com.deveyk.northwind.auth.config;

import com.deveyk.northwind.auth.filter.JwtAuthFilter;
import com.deveyk.northwind.auth.service.jwt.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 SecurityConfig class for Spring Security settings.
 <p>
 * This class customizes the security behavior and enforces role-based access control
 * for various endpoints in the application. </p>
 * <p>
 * It configures the following:
 * - Disabling CSRF for non-browser-based client support.
 * - Disabling frame options for H2 console.
 * - Setting up authentication and authorization rules for specific API endpoints.
 * - Configuring JWT authentication through a custom filter.
 * - Defining session management policy as stateless for REST API support.
 * - Integrating a custom user details service for user authentication.
 * - Configuring a `PasswordEncoder` for password encoding.
 * - Providing an `AuthenticationManager` for managing authentication processes.
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    /**
     * <p>
     * Configures the security filter chain for the application, setting up CSRF protection,
     * HTTP headers, basic authentication, request authorization rules, session management,
     * and JWT authentication filtering.
     * </p>
     * @param http an instance of {@link HttpSecurity} used to configure security settings.
     *             It manages security features like authentication, authorization, and session handling.
     * @return a {@link SecurityFilterChain} that applies the defined security configuration to incoming HTTP requests.
     * @throws Exception in case of any configuration errors during the setup of the security filter chain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/customers/**").hasAnyRole("CUSTOMER", "SALES", "ADMIN")
                        .requestMatchers("/api/employees/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers("/api/products/**").hasAnyRole("ADMIN", "WAREHOUSE", "SALES")
                        .requestMatchers("/api/categories/**").hasAnyRole("ADMIN", "WAREHOUSE", "SALES")
                        .requestMatchers("/api/suppliers/**").hasAnyRole("ADMIN", "WAREHOUSE", "SALES")
                        .requestMatchers("/api/orders/**").hasAnyRole("ADMIN", "SALES")
                        .requestMatchers("/api/hr/**").hasAnyRole("ADMIN", "HR")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    /**
     * <p>
     * Creates and provides a bean for password encoding using BCrypt.
     * The {@link BCryptPasswordEncoder} is used to securely hash passwords.
     * </p>
     * @return an instance of {@link PasswordEncoder} which utilizes BCrypt for password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * <p>
     * Provides a bean of type {@link AuthenticationProvider} for configuring authentication mechanisms.
     * This method utilizes {@link DaoAuthenticationProvider} to authenticate users with credentials
     * from the configured {@link CustomUserDetailsService} and encodes passwords using the specified
     * {@link PasswordEncoder}.
     * </p>
     * @return an instance of {@link AuthenticationProvider} configured with user details service
     *         and password encoder to handle authentication logic.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * <p>
     * Creates and provides a bean of type {@link AuthenticationManager}, which is responsible for
     * handling authentication operations within the application. This method retrieves the
     * AuthenticationManager from the provided {@link AuthenticationConfiguration}.
     * </p>
     * @param authenticationConfiguration an instance of {@link AuthenticationConfiguration}
     *                                     used to configure and retrieve the AuthenticationManager.
     * @return an instance of {@link AuthenticationManager} configured for the application.
     * @throws Exception if an error occurs during the retrieval of the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
