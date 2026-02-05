package com.deveyk.northwind.auth.model.response;

import com.deveyk.northwind.auth.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private boolean disabled;
    private Set<RoleResponse> roles;

}
