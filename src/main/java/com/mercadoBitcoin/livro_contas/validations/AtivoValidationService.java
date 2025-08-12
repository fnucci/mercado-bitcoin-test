package com.mercadoBitcoin.livro_contas.validations;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;

public interface AtivoValidationService {
    void validate(CreateAtivoRequest request);
}
