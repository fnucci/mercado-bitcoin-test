package com.mercadoBitcoin.livro_contas.DTOs.contas.response;

import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;

import java.math.BigDecimal;
import java.util.List;

public record ContaResponse(
        Long id,
        String clientName,
        BigDecimal saldo,
        BigDecimal saldoAtivos,
        List<AtivosEntity> ativos
) {
}
