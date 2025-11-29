package com.tiktok.tiktok_auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tiktok.tiktok_auth.exception.ErrorCode;
import com.tiktok.tiktok_auth.exception.ResponseType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @Builder.Default
    private String code = ErrorCode.SUCCESS.getCode();

    @Builder.Default
    private ResponseType errorType = ResponseType.SUCCESS;

    private String message;

    private T result;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .errorType(ResponseType.SUCCESS)
                .message(message)
                .result(data)
                .build();
    }
}
