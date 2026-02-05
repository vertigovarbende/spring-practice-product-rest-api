package com.deveyk.northwind.auth.service;

import com.deveyk.northwind.auth.model.request.LoginRequest;
import com.deveyk.northwind.auth.model.request.RefreshTokenRequest;
import com.deveyk.northwind.auth.model.request.RegisterRequest;
import com.deveyk.northwind.auth.model.response.AuthResponse;
import com.deveyk.northwind.auth.model.response.CurrentUserResponse;
import org.springframework.security.core.Authentication;

public interface IAuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    CurrentUserResponse me(Authentication authentication);

}
