package com.mercadoBitcoin.livro_contas.validations.impl;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidContaException;
import com.mercadoBitcoin.livro_contas.validations.ContaValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateContaRequest implements ContaValidationService {
    @Override
    public void validate(CreateContaRequest request) {
        log.info("Validando o DTO da requisicao de contas");
        if (request.nome().isEmpty()) {
            throw new InvalidContaException("O nome do cliente e obrigatorio.");
        }
        if (request.cpf().isEmpty()){
            throw new InvalidContaException("O cpf do cliente e obrigatorio.");
        }
    }
}
