package com.tiktok.tiktok_auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // ============== 2xx ==============
    SUCCESS(Code.SUCCESS, ResponseType.SUCCESS,
            "Request completed successfully", HttpStatus.OK),

    // ============== 4xx chung ==============
    BAD_REQUEST(Code.BAD_REQUEST, ResponseType.VALIDATION_ERROR,
            "Bad request", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED(Code.UNAUTHENTICATED, ResponseType.AUTHENTICATION_ERROR,
            "Authentication failed", HttpStatus.UNAUTHORIZED),

    FORBIDDEN(Code.FORBIDDEN, ResponseType.AUTHORIZATION_ERROR,
            "Access forbidden", HttpStatus.FORBIDDEN),

    NOT_FOUND(Code.NOT_FOUND, ResponseType.NOT_FOUND_ERROR,
            "Resource not found", HttpStatus.NOT_FOUND),

    CONFLICT(Code.CONFLICT, ResponseType.CONFLICT_ERROR,
            "Conflict", HttpStatus.CONFLICT),

    // ============== 4xx domain ==============
    USER_EXISTED(Code.USER_EXISTED, ResponseType.CONFLICT_ERROR,
            "User already exists", HttpStatus.BAD_REQUEST),

    USER_NOT_EXISTED(Code.USER_NOT_EXISTED, ResponseType.NOT_FOUND_ERROR,
            "User not found", HttpStatus.NOT_FOUND),

    // validate input
    VALIDATION_FAILED(Code.VALIDATION_FAILED, ResponseType.VALIDATION_ERROR,
            "Validation failed", HttpStatus.BAD_REQUEST),

    // ============== 5xx ==============
    INTERNAL_SERVER_ERROR(Code.INTERNAL_SERVER_ERROR, ResponseType.INTERNAL_SERVER_ERROR,
            "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    // ====== fields ======
    private final String code;           // TIKTOK-xxxxxx
    private final ResponseType responseType;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, ResponseType responseType,
              String message, HttpStatus httpStatus) {
        this.code = code;
        this.responseType = responseType;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getStatusValue() {
        return httpStatus.value();
    }

    public boolean isClientError() {
        return httpStatus.is4xxClientError();
    }

    public boolean isServerError() {
        return httpStatus.is5xxServerError();
    }

    public boolean isSuccess() {
        return httpStatus.is2xxSuccessful();
    }

    // ====== Inner class Code – chỉ giữ string code ======
    public static final class Code {

        // 2xx
        public static final String SUCCESS = "TIKTOK-000000";

        // 4xx chung
        public static final String BAD_REQUEST   = "TIKTOK-000400";
        public static final String UNAUTHENTICATED = "TIKTOK-000401";
        public static final String FORBIDDEN     = "TIKTOK-000403";
        public static final String NOT_FOUND     = "TIKTOK-000404";
        public static final String CONFLICT      = "TIKTOK-000409";

        // 4xx domain – auth/user module
        public static final String USER_EXISTED     = "TIKTOK-001001";
        public static final String USER_NOT_EXISTED = "TIKTOK-001002";
        public static final String VALIDATION_FAILED = "TIKTOK-001003";

        // 5xx
        public static final String INTERNAL_SERVER_ERROR = "TIKTOK-000500";

        private Code() {
        }
    }
}
