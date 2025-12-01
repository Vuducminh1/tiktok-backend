package com.tiktok.tiktok_auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username không được trống")
    @Size(min = 3, max = 50, message = "Username phải từ 3 đến 50 ký tự")
    private String username;

    @NotBlank(message = "Mật khẩu không được trống")
    @Size(min = 6, message = "Mật khẩu phải ít nhất 6 ký tự")
    private String password;
}
