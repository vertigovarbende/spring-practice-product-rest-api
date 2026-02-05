package com.deveyk.northwind.auth.mapper;

import com.deveyk.northwind.auth.model.entity.User;
import com.deveyk.northwind.auth.model.request.UserRequest;
import com.deveyk.northwind.auth.model.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    User toEntity(UserRequest userCreateRequest);

}
