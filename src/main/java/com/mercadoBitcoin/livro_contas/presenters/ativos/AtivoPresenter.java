package com.mercadoBitcoin.livro_contas.presenters.ativos;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.response.AtivosResponse;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;

public class AtivoPresenter {

    public static AtivosResponse toResponse(AtivosEntity entity) {
        return new AtivosResponse(entity.getId(), entity.getNameAtivo(), entity.getValor(), entity.getIsDisponivelVenda(), entity.getConta().getId());
    }
}
