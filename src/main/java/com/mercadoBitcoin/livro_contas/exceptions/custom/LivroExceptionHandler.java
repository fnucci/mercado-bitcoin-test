package com.mercadoBitcoin.livro_contas.exceptions.custom;

import com.mercadoBitcoin.livro_contas.exceptions.ApiErrorArray;
import com.mercadoBitcoin.livro_contas.exceptions.utils.ApiErrorBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LivroExceptionHandler {

    @ExceptionHandler({
            SaldoInsuficienteException.class
    })
    public ResponseEntity<ApiErrorArray> handleAtivoBadRequestExceptions(RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<ApiErrorArray> buildErrorResponse(HttpStatus status, Exception ex, HttpServletRequest request) {
        return ApiErrorBuilder.build(status, ex.getMessage(), request);
    }
}
