package com.deveyk.northwind.auth.controller;

import com.deveyk.northwind.auth.model.request.LoginRequest;
import com.deveyk.northwind.auth.model.request.RefreshTokenRequest;
import com.deveyk.northwind.auth.model.request.RegisterRequest;
import com.deveyk.northwind.auth.model.response.AuthResponse;
import com.deveyk.northwind.auth.model.response.CurrentUserResponse;
import com.deveyk.northwind.common.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication",
     description = "Authentication API endpoints for user login, registration, token refresh, and current user information")
public interface IAuthController {

    @Operation(
            summary = "User login",
            description = "Authenticates a user with username and password and returns access token, refresh token, and user information",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials",
                    content =  @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<AuthResponse>> login(
            @Parameter(description = "Login credentials (username and password)", required = true)
            @Valid @RequestBody LoginRequest loginRequest
    );

    @Operation(
            summary = "User registration",
            description = "Registers a new user with username, password, email, first name, and last name",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict - User already exists",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<AuthResponse>> register(
            @Parameter(description = "User registration information (username, password, email, firstName, lastName)", required = true)
            @Valid @RequestBody RegisterRequest registerRequest
    );

    @Operation(
            summary = "Refresh access token",
            description = "Refreshes the access token using a valid refresh token",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Token refreshed successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid or expired refresh token",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<AuthResponse> refresh(
            @Parameter(description = "Refresh token request", required = true)
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest
    );

    @Operation(
            summary = "Get current user information",
            description = "Returns the authenticated user's information including name, username, email, and roles",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Current user information retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CurrentUserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<CurrentUserResponse> me(
            @Parameter(description = "Spring Security Authentication object (automatically injected)", hidden = true)
            Authentication authentication
    );


}
