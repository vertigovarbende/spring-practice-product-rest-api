package com.deveyk.northwind.auth.model.request;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request model for creating or updating a user by admin")
public class UserRequest {

    @Schema(
            description = "Username of the user",
            example = "johndoe",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 3,
            maxLength = 50
    )
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Schema(
            description = "Password of the user",
            example = "password123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 6,
            format = "password"
    )
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be minimum 6 characters")
    private String password;

    @Schema(
            description = "Email address of the user",
            example = "john.doe@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "email"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Schema(
            description = "First name of the user",
            example = "John",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(
            description = "Last name of the user",
            example = "Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Last name is required")
    private String lastName;

    @ArraySchema(
            arraySchema = @Schema(
                    description = "Set of roles assigned to the user"
            ),
            uniqueItems = true,
            schema = @Schema(implementation = RoleRequest.class)
    )
    @Valid
    private Set<RoleRequest> roles;

}