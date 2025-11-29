package com.tiktok.tiktok_auth.exception;

public enum ResponseType {
    SUCCESS,
    VALIDATION_ERROR,
    AUTHENTICATION_ERROR,
    AUTHORIZATION_ERROR,
    NOT_FOUND_ERROR,
    CONFLICT_ERROR,
    INTERNAL_SERVER_ERROR,
    TOO_MANY_REQUESTS
}
