package org.quickresponse.qr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse<T> {

    private T data;
    private Integer code;
    private String message;

    public static <Void> DefaultResponse<Void> empty() {
        return new DefaultResponse<>(null, null, null);
    }

    public static <Void> DefaultResponse<Void> message(String message) {
        return new DefaultResponse<>(null, null, message);
    }

    public static <T> DefaultResponse<T> of(T data) {
        return new DefaultResponse<>(data, null, null);
    }

    public static <T> DefaultResponse<T> of(T data, String message) {
        return new DefaultResponse<>(data, null, message);
    }

    public static <Void> DefaultResponse<Void> error(ErrorCode errorCode) {
        return new DefaultResponse<>(null, errorCode.getCode(), errorCode.getMessage());
    }
}
