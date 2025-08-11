package com.mercadoBitcoin.livro_contas.presenters.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.response.ContaResponse;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;

public class ContaPresenter {

    public static ContaResponse toResponse(ContasEntity entity) {
        return new ContaResponse(entity.getId(), entity.getClientName(), entity.getSaldo(), entity.getSaldoAtivos(), entity.getAtivos());
    }
}
