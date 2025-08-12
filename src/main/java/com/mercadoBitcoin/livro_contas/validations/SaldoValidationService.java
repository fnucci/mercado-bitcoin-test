package com.mercadoBitcoin.livro_contas.validations;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;

public interface SaldoValidationService {

    void validate(SaldoRequest request);
}
