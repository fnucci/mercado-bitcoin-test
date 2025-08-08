package com.mercadoBitcoin.livro_contas.DTOs.ativos.response;

import java.math.BigDecimal;

public record AtivosResponse(
        Long id,
        String nomeAtivo,
        BigDecimal valorAtivo,
        Boolean isDisponivelVenda,
        Long contaId
) {
}
