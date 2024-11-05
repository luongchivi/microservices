package com.luongchivi.user_profile_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(1111, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_MESSAGE_KEY(1004, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    FORBIDDEN_ACCESS(1008, "You do not have permission to access this resource", HttpStatus.FORBIDDEN),
    PAGE_INVALID(1014, "page must be greater than one", HttpStatus.BAD_REQUEST),
    PAGE_SIZE_INVALID(1015, "pageSize must be greater than one", HttpStatus.BAD_REQUEST),
    USER_PROFILE_NOT_FOUND(1016, "UserProfile not found", HttpStatus.NOT_FOUND),
    BAD_REQUEST(1017, "Bad request", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
