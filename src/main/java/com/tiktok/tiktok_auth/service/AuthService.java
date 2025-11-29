package com.tiktok.tiktok_auth.service;

import com.tiktok.tiktok_auth.dto.request.AuthRequest;
import com.tiktok.tiktok_auth.dto.response.AuthResponse;
import com.tiktok.tiktok_auth.entity.Role;
import com.tiktok.tiktok_auth.entity.User;
import com.tiktok.tiktok_auth.exception.AppException;
import com.tiktok.tiktok_auth.exception.ErrorCode;
import com.tiktok.tiktok_auth.repository.UserRepository;
import com.tiktok.tiktok_auth.repository.UserServicePackageRepository;
import com.tiktok.tiktok_auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserServicePackageRepository userServicePackageRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Thành viên (Subscription) = có ít nhất 1 gói còn hạn
        boolean isSubscription = userServicePackageRepository
                .existsByUserAndActiveIsTrueAndEndAtAfter(user, LocalDateTime.now());

        String token = jwtUtil.generateToken(user.getUsername(), isSubscription);

        return AuthResponse.builder()
                .token(token)
                .subscription(isSubscription)
                .build();
    }
}
