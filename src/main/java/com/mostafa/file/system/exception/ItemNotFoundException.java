package com.mostafa.file.system.exception;

public class ItemNotFoundException extends FileDomainException{
    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

