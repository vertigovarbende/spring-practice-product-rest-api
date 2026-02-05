package com.deveyk.northwind.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request model for user registration")
public class RegisterRequest {

    @Schema(
            description = "Username for the new user account",
            example = "johndoe",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 3,
            maxLength = 50
    )
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Schema(
            description = "Password for the new user account",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "password123",
            minLength = 6,
            format = "password"
    )
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be minimum 6 characters")
    private String password;

    @Schema(
            description = "Email address of the user",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "john.doe@example.com",
            format = "email"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Schema(
            description = "First name of the user",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "John"
    )
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(
            description = "Last name of the user",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Doe"
    )
    @NotBlank(message = "Last name is required")
    private String lastName;


}
