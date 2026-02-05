package com.deveyk.northwind.auth.controller.impl;

import com.deveyk.northwind.auth.controller.IAuthController;
import com.deveyk.northwind.auth.model.request.LoginRequest;
import com.deveyk.northwind.auth.model.request.RefreshTokenRequest;
import com.deveyk.northwind.auth.model.request.RegisterRequest;
import com.deveyk.northwind.auth.model.response.AuthResponse;
import com.deveyk.northwind.auth.model.response.CurrentUserResponse;
import com.deveyk.northwind.auth.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements IAuthController {

    private final IAuthService authService;

    /**
     * Authenticates a user and generates authentication tokens using the provided login request.
     *
     * @param loginRequest the login request containing the user's username and password
     * @return a {@code ResponseEntity} containing the authentication response wrapped in an {@code EntityModel},
     *         which includes tokens and user information
     */
    // POST - login - /api/auth/login
    @Override
    @PostMapping("/login")
    public ResponseEntity<EntityModel<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = this.authService.login(loginRequest);
        return ResponseEntity.ok(toModelForLogin(authResponse));
    }

    /**
     * Registers a new user with the provided registration details and returns an authentication response.
     *
     * @param registerRequest the registration request containing the new user's details such as username, password, email,
     *                        first name, and last name
     * @return a {@code ResponseEntity} containing the authentication response wrapped in an {@code EntityModel},
     *         which includes tokens and user information
     */
    // POST - register - /api/auth/register
    @Override
    @PostMapping("/register")
    public ResponseEntity<EntityModel<AuthResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = this.authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModelForRegister(authResponse));
    }

    /**
     * Retrieves the information of the currently authenticated user.
     *
     * @param authentication the authentication object containing details of the currently authenticated user
     * @return a {@code ResponseEntity} containing the currently authenticated user's details wrapped in a {@code CurrentUserResponse}
     */
    // GET - me - /api/auth/me
    @Override
    @GetMapping("/me")
    public ResponseEntity<CurrentUserResponse> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;    // service??
        }
        CurrentUserResponse currentUserResponse = this.authService.me(authentication);
        return ResponseEntity.ok(currentUserResponse);
    }

    /**
     * Refreshes the authentication tokens using the provided refresh token request.
     *
     * @param refreshTokenRequest the request containing the refresh token used to obtain new access and refresh tokens
     * @return a {@code ResponseEntity} containing the authentication response with updated tokens and user information
     */
    // POST - refresh - /api/auth/refresh
    @Override
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthResponse authResponse = this.authService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(authResponse);
    }

    /**
     * Converts the authentication response object into an {@code EntityModel},
     * adding HATEOAS links for the "me" and "refresh" endpoints.
     *
     * @param authResponse the authentication response object containing user details and tokens
     * @return an {@code EntityModel<AuthResponse>} containing the authentication response
     *         and HATEOAS links for related actions
     */
    private EntityModel<AuthResponse> toModelForLogin(AuthResponse authResponse) {
        return EntityModel.of(authResponse,
                linkTo(methodOn(AuthController.class).me(null)).withRel("me")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(AuthController.class).refresh(null)).withRel("refresh")
                        .withType(HttpMethod.POST.name())
        );
    }

    /**
     * Converts the authentication response object into an {@code EntityModel},
     * adding HATEOAS links for the "login" endpoint.
     *
     * @param authResponse the authentication response object containing user details and tokens
     * @return an {@code EntityModel<AuthResponse>} containing the authentication response
     *         and HATEOAS links for related actions
     */
    private EntityModel<AuthResponse> toModelForRegister(AuthResponse authResponse) {
        return EntityModel.of(authResponse,
                linkTo(methodOn(AuthController.class).login(null)).withRel("login")
                        .withType(HttpMethod.POST.name())
                );
    }
}