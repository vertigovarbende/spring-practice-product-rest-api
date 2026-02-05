package com.deveyk.northwind.auth.service.impl;

import com.deveyk.northwind.auth.model.entity.Role;
import com.deveyk.northwind.auth.model.entity.User;
import com.deveyk.northwind.auth.model.enums.RoleType;
import com.deveyk.northwind.auth.model.enums.TokenType;
import com.deveyk.northwind.auth.model.request.LoginRequest;
import com.deveyk.northwind.auth.model.request.RefreshTokenRequest;
import com.deveyk.northwind.auth.model.request.RegisterRequest;
import com.deveyk.northwind.auth.model.response.AuthResponse;
import com.deveyk.northwind.auth.model.response.CurrentUserResponse;
import com.deveyk.northwind.auth.repository.RoleRepository;
import com.deveyk.northwind.auth.repository.UserRepository;
import com.deveyk.northwind.auth.service.IAuthService;
import com.deveyk.northwind.auth.service.jwt.CustomUserDetailsService;
import com.deveyk.northwind.auth.service.jwt.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Authenticates a user based on the provided login credentials and returns an authentication response
     * containing user details and generated JWT tokens.
     *
     * @param loginRequest the login request containing username and password
     * @return an {@code AuthResponse} object containing username, email, roles, access token, and refresh token
     * @throws EntityNotFoundException if the user does not exist in the system
     */
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = this.userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found")); // UsernameNotFoundException?
        Set<String> roles = getRoles(authentication);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        return AuthResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Registers a new user into the system with default configurations, such as assigning the
     * "ROLE_EMPLOYEE" role and setting the account as disabled until admin approval.
     *
     * @param registerRequest the registration request containing the user's details such as username,
     *                        password, email, first name, and last name
     * @return an {@code AuthResponse} object containing the registered user's username, email,
     *         and roles
     * @throws EntityExistsException if a user with the provided username already exists
     * @throws EntityNotFoundException if the default role ("ROLE_EMPLOYEE") is not found in the system
     */
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (this.userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new EntityExistsException("Username already exists");
        }
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .disabled(true) // admin will enable this user and change its role
                .build();
        Role userRole = this.roleRepository.findByName(RoleType.ROLE_EMPLOYEE)  // by default
                .orElseThrow(() -> new EntityNotFoundException("Role Not Found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        this.userRepository.save(user);
        return AuthResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(getRoles(user))
                .build();
    }

    /**
     * Refreshes the access token using a valid refresh token.
     * If the refresh token is valid and of the correct type, generates a new access token
     * and returns an authentication response with updated token details.
     *
     * @param refreshTokenRequest the request containing the refresh token
     * @return an {@code AuthResponse} object containing the username, roles, new access token,
     *         and the provided refresh token
     * @throws JwtException if the token type is invalid or the token is not valid
     */
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        if (!this.jwtService.extractType(refreshToken).equals(TokenType.REFRESH_TOKEN.name())) {
            throw new JwtException("Token type is not valid"); // it might work?
        }
        String username = this.jwtService.extractUsername(refreshToken);
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
        if (this.jwtService.isTokenValid(refreshToken, userDetails)) {
            String newAccessToken = this.jwtService.generateAccessToken(userDetails);
            Set<String> roles = getRoles(userDetails);

            return AuthResponse.builder()
                    .username(username)
                    .roles(roles)
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new JwtException("Token is not valid"); // ??
        }
    }

    /**
     * Retrieves the details of the currently authenticated user, including their profile information
     * and roles, based on the provided authentication context.
     *
     * @param authentication the authentication object containing the security context of the currently logged-in user
     * @return a {@code CurrentUserResponse} object containing the authenticated user's first name, last name,
     *         username, email, and roles
     * @throws EntityNotFoundException if the user is not found in the system
     */
    @Override
    public CurrentUserResponse me(Authentication authentication) {
        User user = this.userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found")); // UsernameNotFoundException?
        Set<String> roles = getRoles(authentication);
        return CurrentUserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }

    // Are these methods necessary?
    private Set<String> getRoles(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    private Set<String> getRoles(User user) {
        return user.getRoles()
                .stream()
                .map(roles -> roles.getName().name())
                .collect(Collectors.toSet());
    }

    private Set<String> getRoles(UserDetails userDetails) {
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

}
