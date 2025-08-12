package com.mercadoBitcoin.livro_contas.exceptions.utils;

import com.mercadoBitcoin.livro_contas.exceptions.ApiErrorArray;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorBuilder {

    public static ResponseEntity<ApiErrorArray> build(HttpStatus status, String message, HttpServletRequest request) {
        return build(status, List.of(message), request);
    }

    public static ResponseEntity<ApiErrorArray> build(HttpStatus status, List<String> messages,
                                                      HttpServletRequest request) {
        ApiErrorArray error = new ApiErrorArray(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                messages,
                request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
