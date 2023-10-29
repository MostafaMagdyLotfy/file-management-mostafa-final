package com.mostafa.file.system.exception.handler;

import com.mostafa.file.system.entity.dto.ErrorDTO;
import com.mostafa.file.system.exception.FileDomainException;
import com.mostafa.file.system.exception.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(2)
public class FileGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {FileDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(FileDomainException fileDomainException) {
        log.error(fileDomainException.getMessage(), fileDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(fileDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(FileNotFoundException fileNotFoundException) {
        log.error(fileNotFoundException.getMessage(), fileNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(fileNotFoundException.getMessage())
                .build();
    }
}