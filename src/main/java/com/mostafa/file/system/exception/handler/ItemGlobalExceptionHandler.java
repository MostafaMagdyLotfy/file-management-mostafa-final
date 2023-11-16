package com.mostafa.file.system.exception.handler;

import com.mostafa.file.system.entity.dto.ErrorDTO;
import com.mostafa.file.system.exception.ItemDomainException;
import com.mostafa.file.system.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(1)
public class ItemGlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {ItemDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ItemDomainException itemDomainException) {
        log.error(itemDomainException.getMessage(), itemDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(itemDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(ItemNotFoundException itemNotFoundException) {
        log.error(itemNotFoundException.getMessage(), itemNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(itemNotFoundException.getMessage())
                .build();
    }
}