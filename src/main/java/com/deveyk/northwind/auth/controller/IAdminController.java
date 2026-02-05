package com.deveyk.northwind.auth.controller;

import com.deveyk.northwind.auth.model.request.PasswordPatchRequest;
import com.deveyk.northwind.auth.model.request.UserRequest;
import com.deveyk.northwind.auth.model.response.UserResponse;
import com.deveyk.northwind.common.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Tag(
        name = "Admin",
        description = "Admin API endpoints for managing users and viewing system information"
)
public interface IAdminController {

    @Operation(
            summary = "Get user by ID (admin)",
            description = "Retrieves a user by ID along with the requesting admin username and a timestamp.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User retrieved successfully",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Map<String, Object>> getUser(
            @Parameter(description = "ID of the user to retrieve", required = true)
            @PathVariable Long id,
            @Parameter(description = "Spring Security Authentication object (automatically injected)", hidden = true)
            Authentication authentication
    );

    @Operation(
            summary = "Get all users (admin)",
            description = "Returns a paginated list of all users in the system.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully",
                    content = @Content(
                            mediaType = "application/json"
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
    ResponseEntity<CollectionModel<EntityModel<UserResponse>>> getAllUsers(
            @Parameter(description = "Pagination information (page, size, sort)", required = false)
            Pageable pageable
    );

    @Operation(
            summary = "Create a new user (admin)",
            description = "Creates a new user and returns the created user with HATEOAS links.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request - Invalid user data",
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
    ResponseEntity<EntityModel<UserResponse>> createUser(
            @Parameter(description = "User information for creating a new user", required = true)
            @Valid @RequestBody UserRequest userCreateRequest
    );

    @Operation(
            summary = "Delete user by ID (admin)",
            description = "Deletes a user by ID. This operation cannot be undone.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "User deleted successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "ID of the user to delete", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Update user information (admin)",
            description = "Updates user information for the given user ID and returns the updated user with HATEOAS links.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request - Invalid user data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
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
    ResponseEntity<EntityModel<UserResponse>> updateUser(
            @Parameter(description = "ID of the user to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated user information", required = true)
            @Valid @RequestBody UserRequest userRequest
    );

    @Operation(
            summary = "Enable user (admin)",
            description = "Enables the user account for the given user ID.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "User enabled successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Void> enableUser(
            @Parameter(description = "ID of the user to enable", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Disable user (admin)",
            description = "Disables the user account for the given user ID.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "User disabled successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Void> disableUser(
            @Parameter(description = "ID of the user to disable", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Change user password (admin)",
            description = "Changes the password for the given user ID and returns the updated user with HATEOAS links.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password changed successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request - Invalid password data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
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
    ResponseEntity<EntityModel<UserResponse>> changeUserPassword(
            @Parameter(description = "ID of the user whose password will be changed", required = true)
            @PathVariable Long id,
            @Parameter(description = "New password information", required = true)
            @Valid @RequestBody PasswordPatchRequest passwordPatchRequest
    );

    @Operation(
            summary = "Get system information (admin)",
            description = "Returns basic system information such as Java version, OS name, and the requesting admin username.",
            tags = {"Admin"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "System information retrieved successfully",
                    content = @Content(
                            mediaType = "application/json"
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
    ResponseEntity<Map<String, Object>> systemInfo(
            @Parameter(description = "Spring Security Authentication object (automatically injected)", hidden = true)
            Authentication authentication
    );

}
