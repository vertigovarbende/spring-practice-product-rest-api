package com.deveyk.northwind.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request model for changing a user's password by admin")
public class PasswordPatchRequest {

    @Schema(
            description = "New password for the user",
            example = "newStrongPassword123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 6,
            format = "password"
    )
    @NotBlank(message = "New password is required")
    @Size(min = 6, message = "Password must be minimum 6 characters")
    private String newPassword;

}
