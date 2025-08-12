package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class SaldoNuloException extends RuntimeException {
    public SaldoNuloException(String msg) {
        super(msg);
    }
}
