package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}
