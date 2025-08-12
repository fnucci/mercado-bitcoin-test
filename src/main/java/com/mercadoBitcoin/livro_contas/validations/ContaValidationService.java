package com.mercadoBitcoin.livro_contas.validations;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;

public interface ContaValidationService
{
    void validate(CreateContaRequest request);
}
