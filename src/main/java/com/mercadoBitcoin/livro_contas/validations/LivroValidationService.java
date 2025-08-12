package com.mercadoBitcoin.livro_contas.validations;

import com.mercadoBitcoin.livro_contas.DTOs.livro.LivroDeOrdem;

public interface LivroValidationService {
    void validate(LivroDeOrdem livro);
}
