package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class AtivoNotFoundException extends RuntimeException {

    public AtivoNotFoundException(Long id) {
        super(String.format("O ativo com id %s nao localizado", id));
    }
}
