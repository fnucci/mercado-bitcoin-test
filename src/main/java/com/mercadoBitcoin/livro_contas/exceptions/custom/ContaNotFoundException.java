package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class ContaNotFoundException extends RuntimeException {

    public ContaNotFoundException(Long id) {
        super(String.format("Conta com id %s nao encontrada", id));
    }
}
