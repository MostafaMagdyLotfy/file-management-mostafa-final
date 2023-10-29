package com.mostafa.file.system.exception;

public class ItemDomainException extends DomainException {
    public ItemDomainException(String message) {
        super(message);
    }

    public ItemDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
