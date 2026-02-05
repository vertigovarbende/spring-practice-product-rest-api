package com.deveyk.northwind.auth.service;

import com.deveyk.northwind.auth.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdminService {

    User getUser(Long id);

    Page<User> getAllUsers(Pageable pageable);

    User createUser(User user);

    void deleteUser(Long id);

    User updateUser(User user);

    void enableUser(Long id);

    void disableUser(Long id);

    User changeUserPassword(Long id, String newPassword);

}
