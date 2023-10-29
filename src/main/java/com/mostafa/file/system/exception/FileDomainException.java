package com.mostafa.file.system.exception;

public class FileDomainException extends DomainException {
    public FileDomainException(String message) {
        super(message);
    }

    public FileDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
