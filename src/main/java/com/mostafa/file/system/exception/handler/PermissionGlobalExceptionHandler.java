package com.mostafa.file.system.exception.handler;

import com.mostafa.file.system.entity.dto.ErrorDTO;
import com.mostafa.file.system.exception.PermissionDomainException;
import com.mostafa.file.system.exception.PermissionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(3)
public class PermissionGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {PermissionDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(PermissionDomainException permissionDomainException) {
        log.error(permissionDomainException.getMessage(), permissionDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(permissionDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {PermissionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(PermissionNotFoundException permissionNotFoundException) {
        log.error(permissionNotFoundException.getMessage(), permissionNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(permissionNotFoundException.getMessage())
                .build();
    }
}