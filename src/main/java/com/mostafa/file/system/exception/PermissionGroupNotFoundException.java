package com.mostafa.file.system.exception;

public class PermissionGroupNotFoundException extends FileDomainException{
    public PermissionGroupNotFoundException(String message) {
        super(message);
    }

    public PermissionGroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

