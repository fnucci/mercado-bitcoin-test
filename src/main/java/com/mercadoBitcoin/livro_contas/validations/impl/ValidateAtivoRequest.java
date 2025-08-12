package com.mercadoBitcoin.livro_contas.validations.impl;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidAtivoException;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidContaException;
import com.mercadoBitcoin.livro_contas.validations.AtivoValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ValidateAtivoRequest implements AtivoValidationService {

    @Override
    public void validate(CreateAtivoRequest request) {
        log.info("Validando o DTO da requisicao de contas");
        if (request.nomeAtivo().isEmpty()) {
            throw new InvalidAtivoException("O nome do ativo e obrigatorio.");
        }
        if (request.valorAtivo() == null || request.valorAtivo().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAtivoException("O valor do ativo e obrigatorio.");
        }
        if(request.isDisponivelVenda() == null){
            throw new InvalidAtivoException("O indicador de disponibilidade de venda e obrigatorio");
        }
        if(request.contaId() == null || request.contaId() <= 0){
            throw new InvalidAtivoException("O id de conta e obrigatorio");
        }
    }
}
