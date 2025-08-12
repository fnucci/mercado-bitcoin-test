package com.mercadoBitcoin.livro_contas.exceptions.custom;

public class InvalidAtivoException extends RuntimeException{

    public InvalidAtivoException(String msg){
        super(msg);
    }
}
