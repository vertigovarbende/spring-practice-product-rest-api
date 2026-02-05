package com.deveyk.northwind.auth.controller.impl;

import com.deveyk.northwind.auth.controller.IAdminController;
import com.deveyk.northwind.auth.mapper.UserMapper;
import com.deveyk.northwind.auth.model.entity.User;
import com.deveyk.northwind.auth.model.request.PasswordPatchRequest;
import com.deveyk.northwind.auth.model.request.UserRequest;
import com.deveyk.northwind.auth.model.response.UserResponse;
import com.deveyk.northwind.auth.service.IAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController implements IAdminController {

    private final IAdminService adminService;
    private final UserMapper userMapper;

    /**
     * Retrieves information about a specific user based on the provided user ID.
     *
     * @param id the ID of the user to retrieve
     * @param authentication the authentication object containing the details of the authenticated admin
     * @return a {@code ResponseEntity} containing a map with the user details, the admin's name, and a timestamp
     */
    // GET - getUser - /api/admin/users/{id}
    @Override
    @GetMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id,
                                                       Authentication authentication) {

        UserResponse userResponse = userMapper.toResponse(adminService.getUser(id));

        EntityModel<UserResponse> entityModelOfUserResponse = EntityModel.of(userResponse,
                linkTo(methodOn(AdminController.class).getAllUsers(null)).withRel("users")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(AdminController.class).enableUser(null)).withRel("enable")
                        .withType(HttpMethod.PATCH.name())
        );
        return ResponseEntity.ok(Map.of(
                        "user", entityModelOfUserResponse,
                        "admin", authentication.getName(),
                        "timestamp", LocalDateTime.now()
                )
        );
    }

    /**
     * Retrieves a pageable collection of all users.
     *
     * @param pageable the pagination and sorting information
     * @return a {@code ResponseEntity} containing a {@code CollectionModel} of {@code EntityModel} objects wrapping user responses
     */
    // GET - getAllUsers - /api/admin/users - pageable?
    @Override
    @GetMapping("/users")
    public ResponseEntity<CollectionModel<EntityModel<UserResponse>>> getAllUsers(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        List<EntityModel<UserResponse>> users = adminService.getAllUsers(pageable).stream()
                .map(userMapper::toResponse)
                .map(this::toModel)
                .toList();

        CollectionModel<EntityModel<UserResponse>> collectionModelOfUsersResponse = CollectionModel.of(users,
                linkTo(methodOn(AdminController.class).getAllUsers(pageable)).withSelfRel()
        );

        return ResponseEntity.ok(collectionModelOfUsersResponse);

    }

    /**
     * Creates a new user based on the provided user creation request.
     *
     * @param userCreateRequest the request object containing details of the user to be created
     * @return a {@code ResponseEntity} containing an {@code EntityModel} wrapping the created user's response object,
     *         along with relevant HATEOAS links
     */
    // POST - createUser - /api/admin/users
    @Override
    @PostMapping("/users")
    public ResponseEntity<EntityModel<UserResponse>> createUser(
            @Valid @RequestBody UserRequest userCreateRequest
    ) {
        User savedUser = adminService.createUser(userMapper.toEntity(userCreateRequest));
        UserResponse userResponse = userMapper.toResponse(savedUser);

        EntityModel<UserResponse> entityModelOfUserResponse = EntityModel.of(userResponse,
                linkTo(methodOn(AdminController.class).getUser(userResponse.getId(), null)).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(AdminController.class).getAllUsers(null)).withRel("users")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(AdminController.class).enableUser(null)).withRel("enable")
                        .withType(HttpMethod.PATCH.name())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModelOfUserResponse);
    }

    /**
     * Deletes a user based on the provided user ID.
     *
     * @param id the ID of the user to delete
     * @return a {@code ResponseEntity} with no content if the deletion is successful
     */
    // DELETE - deleteUser - /api/admin/users/{id}
    @Override
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing user based on the provided user ID and user details.
     *
     * @param id the ID of the user to update
     * @param userRequest the request object containing updated details for the user
     * @return a {@code ResponseEntity} containing an {@code EntityModel} wrapping the updated user's response object
     */
    // PUT - updateUser - /api/admin/users/{id}
    // ????????
    @Override
    @PutMapping("/users/{id}")
    public ResponseEntity<EntityModel<UserResponse>> updateUser(@PathVariable Long id,
                                                                @Valid @RequestBody UserRequest userRequest) {
        User user = adminService.updateUser(userMapper.toEntity(userRequest));
        EntityModel<UserResponse> userResponse = toModel(userMapper.toResponse(user));
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Enables a user based on the provided user ID.
     *
     * @param id the ID of the user to enable
     * @return a {@code ResponseEntity} with no content if the operation is successful
     */
    // PATCH - enableUser - /api/admin/users/{id}/enable
    @Override
    @PatchMapping("/users/{id}/enable")
    public ResponseEntity<Void> enableUser(@PathVariable Long id){
        adminService.enableUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Disables a user based on the provided user ID.
     *
     * @param id the ID of the user to disable
     * @return a {@code ResponseEntity} with no content if the operation is successful
     */
    // PATCH - disableUser - /api/admin/users/{id}/disable
    @Override
    @PatchMapping("/users/{id}/disable")
    public ResponseEntity<Void> disableUser(@PathVariable Long id){
        adminService.disableUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Changes the password of the specified user.
     *
     * @param id the ID of the user whose password is to be updated
     * @param passwordPatchRequest the request object containing the new password for the user
     * @return a {@code ResponseEntity} containing an {@code EntityModel} of the updated user's response object
     */
    // PATCH - changeUserPassword - /api/admin/users/{id} - to change password??
    // noContent??
    @Override
    @PatchMapping("/users/{id}")
    public ResponseEntity<EntityModel<UserResponse>> changeUserPassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordPatchRequest passwordPatchRequest) {
        String newPassword = passwordPatchRequest.getNewPassword();
        User user = adminService.changeUserPassword(id, newPassword);
        UserResponse userResponse = userMapper.toResponse(user);
        EntityModel<UserResponse> entityModelOfUserResponse = toModel(userResponse);
        return ResponseEntity.ok(entityModelOfUserResponse);
    }

    /**
     * Retrieves system information including Java version, operating system name,
     * the authenticated admin user, and the current timestamp.
     * This endpoint is secured and only accessible to admin users.
     *
     * @param authentication The authentication object containing security details of the currently authenticated user.
     * @return A ResponseEntity containing a map of system information including message, access level, admin user, Java version, OS name, and timestamp.
     */
    // GET - systemInfo - /api/admin/system
    @Override
    @GetMapping("/system")
    public ResponseEntity<Map<String, Object>> systemInfo(Authentication authentication) {
        Map<String, Object> systemInfoMap = Map.of(
                "message", "System information",
                "access", "Admin only - Method-level security",
                "admin", authentication.getName(),
                "javaVersion", System.getProperty("java.version"),
                "osName", System.getProperty("os.name"),
                "timestamp", LocalDateTime.now()
        );
        return ResponseEntity.ok(systemInfoMap);
    }

    /**
     * Converts a UserResponse object into an EntityModel representation.
     *
     * @param userResponse the UserResponse object to be converted into an EntityModel
     * @return an EntityModel containing the UserResponse along with relevant HATEOAS links
     */
    private EntityModel<UserResponse> toModel(UserResponse userResponse) {
        return EntityModel.of(userResponse,
                linkTo(methodOn(AdminController.class).getUser(userResponse.getId(), null)).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(AdminController.class).getAllUsers(null)).withRel("users")
                        .withType(HttpMethod.GET.name())
        );
    }

}
