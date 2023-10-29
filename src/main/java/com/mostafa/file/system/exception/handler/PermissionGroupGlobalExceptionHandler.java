package com.mostafa.file.system.exception.handler;

import com.mostafa.file.system.entity.dto.ErrorDTO;
import com.mostafa.file.system.exception.PermissionGroupDomainException;
import com.mostafa.file.system.exception.PermissionGroupNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(4)
public class PermissionGroupGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {PermissionGroupDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(PermissionGroupDomainException permissionGroupDomainException) {
        log.error(permissionGroupDomainException.getMessage(), permissionGroupDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(permissionGroupDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {PermissionGroupNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(PermissionGroupNotFoundException permissionGroupNotFoundException) {
        log.error(permissionGroupNotFoundException.getMessage(), permissionGroupNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(permissionGroupNotFoundException.getMessage())
                .build();
    }
}