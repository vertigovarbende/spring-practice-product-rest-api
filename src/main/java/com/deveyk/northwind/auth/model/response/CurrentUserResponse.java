package com.deveyk.northwind.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response model containing current authenticated user information")
public class CurrentUserResponse {

    @Schema(
            description = "First name of the user",
            example = "John"
    )
    private String firstName;

    @Schema(
            description = "Last name of the user",
            example = "Doe"
    )
    private String lastName;

    @Schema(
            description = "Username of the user",
            example = "johndoe"
    )
    private String username;

    @Schema(
            description = "Email address of the user",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "Set of roles assigned to the user",
            example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]"
    )
    private Set<String> roles;

}
