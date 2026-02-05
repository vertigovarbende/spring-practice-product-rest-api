package com.deveyk.northwind.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response model containing authentication tokens and user information")
public class AuthResponse {

    @Schema(
            description = "Username of the authenticated user",
            example = "johndoe"
    )
    private String username;

    @Schema(
            description = "Email address of the authenticated user",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "Set of roles assigned to the user",
            example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]"
    )
    private Set<String> roles;

    @Schema(
            description = "JWT access token for API authentication",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String accessToken;

    @Schema(
            description = "JWT refresh token for obtaining new access tokens",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String refreshToken;

}
