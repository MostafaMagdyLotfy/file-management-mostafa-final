package com.mostafa.file.system.exception;

public class PermissionNotFoundException extends FileDomainException{
    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

