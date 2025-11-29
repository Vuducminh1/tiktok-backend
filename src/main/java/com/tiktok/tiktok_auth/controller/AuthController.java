package com.tiktok.tiktok_auth.controller;

import com.tiktok.tiktok_auth.dto.request.AuthRequest;
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
    public ApiResponse<String> register(@RequestBody @Valid AuthRequest request) {
        authService.register(request);
        return ApiResponse.success(null, "Đăng ký thành công");
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ApiResponse.success(authResponse, "Đăng nhập thành công");
    }
}

