package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class InvalidContaException extends RuntimeException {
    public InvalidContaException(String msg) {
        super(msg);
    }
}
