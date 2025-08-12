package com.mercadoBitcoin.livro_contas.validations.impl;

import com.mercadoBitcoin.livro_contas.DTOs.livro.LivroDeOrdem;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidAtivoException;
import com.mercadoBitcoin.livro_contas.validations.LivroValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateLivroDeOrdens implements LivroValidationService {

    @Override
    public void validate(LivroDeOrdem livro) {
        log.info("Validando a compra do ativo");
        if (livro.ativoId() == null) {
            throw new InvalidAtivoException("O id do ativo e obrigatorio");
        }
    }
}
