package com.deveyk.northwind.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request model for user login")
public class LoginRequest {

    @Schema(
            description = "Username of the user",
            example = "johndoe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(
            description = "User password",
            example = "password123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "password"
    )
    @NotBlank(message = "Password is required")
    private String password;


}
