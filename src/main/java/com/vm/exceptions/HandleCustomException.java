package com.vm.exceptions;

import com.vm.dto.ResultDto;
import com.vm.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author TuLa
 */

@RestControllerAdvice
public class HandleCustomException {
    private static final Logger log = LogManager.getLogger(HandleCustomException.class);

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ResponseEntityError> handleApiException(ApiException apiException) {
        log.error(this.getClass().getSimpleName(), apiException);
        ResponseEntityError response = new ResponseEntityError();
        response.setTimestamp(LocalDateTime.now());
        response.setMessage(apiException.getMessage());
        response.setError(apiException.getHttpStatus().name());
        response.setStatus(apiException.getHttpStatus().value());
        return new ResponseEntity<>(response, apiException.getHttpStatus());
    }

    // validation
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ResponseEntityError> handleProcessFormExceptions(BindException exception) {
        log.error(this.getClass().getSimpleName(), exception);
        ResponseEntityError response = new ResponseEntityError();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setInvalidForm(exception.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ResultDto> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.ERROR, userNotFoundException.getMessage());
        return new ResponseEntity<>(resultDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResultDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.ERROR, productNotFoundException.getMessage());
        return new ResponseEntity<>(resultDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoiceItemInValidException.class)
    public ResponseEntity<ResultDto> handleInvoiceItemInValidException(InvoiceItemInValidException invoiceItemInValidException) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.ERROR, invoiceItemInValidException.getMessage());
        return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleExistedException.class)
    public ResponseEntity<ResultDto> handleRoleExistedException(RoleExistedException roleExistedException) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.ERROR, roleExistedException.getMessage());
        return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryInvalidException.class)
    public ResponseEntity<ResultDto> handleCategoryInvalidException(CategoryInvalidException categoryInvalidException) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.ERROR, categoryInvalidException.getMessage());
        return new ResponseEntity<>(resultDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResultDto> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        ResultDto resultDto = new ResultDto(Constants.ResponseKey.ERROR, categoryNotFoundException.getMessage());
        return new ResponseEntity<>(resultDto, HttpStatus.NOT_FOUND);
    }
}
