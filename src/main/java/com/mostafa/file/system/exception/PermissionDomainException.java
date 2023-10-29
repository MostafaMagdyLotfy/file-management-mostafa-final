package com.mostafa.file.system.exception;

public class PermissionDomainException extends DomainException {
    public PermissionDomainException(String message) {
        super(message);
    }

    public PermissionDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
