package org.quickresponse.qr.exception;

import org.quickresponse.qr.service.common.dto.ErrorCode;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SpotAdminException extends RuntimeException {
    private final ErrorCode errorCode;

    public SpotAdminException(final String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SpotAdminException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
