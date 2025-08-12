package com.mercadoBitcoin.livro_contas.exceptions.handlers;

import com.mercadoBitcoin.livro_contas.exceptions.ApiErrorArray;
import com.mercadoBitcoin.livro_contas.exceptions.custom.ContaNotFoundException;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidContaException;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidValueException;
import com.mercadoBitcoin.livro_contas.exceptions.custom.SaldoNuloException;
import com.mercadoBitcoin.livro_contas.exceptions.utils.ApiErrorBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContaExceptionHandler {

    @ExceptionHandler(ContaNotFoundException.class)
    public ResponseEntity<ApiErrorArray> handleContaNotFound(ContaNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler({
            InvalidValueException.class,
            InvalidContaException.class,
            SaldoNuloException.class
    })
    public ResponseEntity<ApiErrorArray> handleContaBadRequestExceptions(RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<ApiErrorArray> buildErrorResponse(HttpStatus status, Exception ex, HttpServletRequest request) {
        return ApiErrorBuilder.build(status, ex.getMessage(), request);
    }
}
