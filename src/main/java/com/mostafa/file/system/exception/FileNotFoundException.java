package com.mostafa.file.system.exception;

public class FileNotFoundException extends FileDomainException{
    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

