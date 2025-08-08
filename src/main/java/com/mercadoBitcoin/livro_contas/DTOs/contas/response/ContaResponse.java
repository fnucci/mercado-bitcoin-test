package com.mercadoBitcoin.livro_contas.DTOs.contas.response;

import java.math.BigDecimal;

public record ContaResponse(
        Long id,
        String clientName,
        BigDecimal saldo,
        BigDecimal saldoAtivos
) {
}
