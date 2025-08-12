package com.mercadoBitcoin.livro_contas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorArray {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;
}
