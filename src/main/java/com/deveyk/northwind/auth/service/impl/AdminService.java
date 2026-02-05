package com.deveyk.northwind.auth.service.impl;

import com.deveyk.northwind.auth.model.entity.Role;
import com.deveyk.northwind.auth.model.entity.User;
import com.deveyk.northwind.auth.model.enums.RoleType;
import com.deveyk.northwind.auth.repository.RoleRepository;
import com.deveyk.northwind.auth.repository.UserRepository;
import com.deveyk.northwind.auth.service.IAdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves a User entity by its unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return the User entity associated with the given identifier
     * @throws EntityNotFoundException if no user is found with the given identifier
     */
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    /**
     * Retrieves a paginated list of all users.
     *
     * @param pageable pagination details including page number, size, and sorting options
     * @return a page containing a list of User entities
     */
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Creates a new user in the system with the specified details and roles.
     * If any of the roles provided are not found, an EntityNotFoundException is thrown.
     * The user's password is encoded before saving, and the user is initially
     * set as disabled.
     *
     * @param user the user object containing the details and roles to be persisted
     * @return the newly created User entity after being saved in the system
     * @throws EntityNotFoundException if any assigned roles are not found in the system
     */
    @Override
    public User createUser(User user) {
        Set<Role> roles = user.getRoles().stream()
                .map(role ->
                        roleRepository.findByName(role.getName()).orElseThrow(() ->
                                new EntityNotFoundException("Role not found")))
                .collect(Collectors.toSet());
        return userRepository.save(User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(roles)
                .disabled(true)
                .build());
    }

    /**
     * Deletes a user from the system based on their unique identifier.
     *
     * @param id the unique identifier of the user to be deleted
     */
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Updates an existing user in the system. The user object provided contains
     * the details to be updated. The user must already exist in the system.
     *
     * @param user the user object containing the updated information
     * @return the updated User entity after being saved in the system
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Enables a user account by setting the `disabled` flag to false.
     * The user is retrieved using the provided identifier, and the updated
     * user entity is saved in the repository.
     *
     * @param id the unique identifier of the user to be enabled
     * @throws EntityNotFoundException if no user is found with the given identifier
     */
    @Override
    public void enableUser(Long id) {
        User user = getUser(id);
        user.setDisabled(false);
        userRepository.save(user);
    }

    /**
     * Disables a user account by setting the `disabled` flag to true.
     * The user is retrieved using the provided identifier, and the updated
     * user entity is saved in the repository.
     *
     * @param id the unique identifier of the user to be disabled
     * @throws EntityNotFoundException if no user is found with the given identifier
     */
    @Override
    public void disableUser(Long id) {
        User user = getUser(id);
        user.setDisabled(true);
        userRepository.save(user);
    }

    /**
     * Changes the password of a user identified by their unique identifier.
     * The new password is encoded before being saved.
     *
     * @param id the unique identifier of the user whose password is to be changed
     * @param newPassword the new password to be set for the user
     * @return the updated User entity after the password change
     * @throws EntityNotFoundException if no user is found with the given identifier
     */
    @Override
    public User changeUserPassword(Long id, String newPassword) {
        User user = getUser(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

}
