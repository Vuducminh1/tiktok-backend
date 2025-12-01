package com.tiktok.tiktok_auth.controller;

import com.tiktok.tiktok_auth.dto.request.LoginRequest;
import com.tiktok.tiktok_auth.dto.request.RegisterRequest;
import com.tiktok.tiktok_auth.dto.response.ApiResponse;
import com.tiktok.tiktok_auth.dto.response.AuthResponse;
import com.tiktok.tiktok_auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return ApiResponse.success(null, "Đăng ký thành công");
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ApiResponse.success(authResponse, "Đăng nhập thành công");
    }
}

