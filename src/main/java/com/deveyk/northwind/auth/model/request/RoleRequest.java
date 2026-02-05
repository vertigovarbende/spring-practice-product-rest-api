package com.deveyk.northwind.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request model for assigning or creating a role")
public class RoleRequest {

    @Schema(
            description = "Name of the role",
            example = "ROLE_EMPLOYEE",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Role name is required")
    private String name;

}
