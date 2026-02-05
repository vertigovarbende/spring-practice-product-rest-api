package com.deveyk.northwind.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request model for refreshing access token")
public class RefreshTokenRequest {

    @Schema(
            description = "Refresh token used to obtain a new access token",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    @NotBlank(message = "Refresh token is required")
    private String refreshToken;

}
