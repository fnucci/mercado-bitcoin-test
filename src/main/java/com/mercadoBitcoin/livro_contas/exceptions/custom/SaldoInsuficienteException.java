package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String msg) {
        super(msg);
    }
}
