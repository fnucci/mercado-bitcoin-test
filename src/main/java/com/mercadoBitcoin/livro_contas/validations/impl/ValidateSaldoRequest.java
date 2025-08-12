package com.mercadoBitcoin.livro_contas.validations.impl;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;
import com.mercadoBitcoin.livro_contas.exceptions.custom.SaldoNuloException;
import com.mercadoBitcoin.livro_contas.validations.SaldoValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateSaldoRequest implements SaldoValidationService {
    @Override
    public void validate(SaldoRequest request) {
        log.info("Validando o valor da requisicao");
        if (request.valor() == null) {
            throw new SaldoNuloException("O saldo deve conter um numero maior do que zero");
        }
    }
}
