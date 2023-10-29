package com.mostafa.file.system.exception;

public class PermissionGroupDomainException extends DomainException {
    public PermissionGroupDomainException(String message) {
        super(message);
    }

    public PermissionGroupDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
