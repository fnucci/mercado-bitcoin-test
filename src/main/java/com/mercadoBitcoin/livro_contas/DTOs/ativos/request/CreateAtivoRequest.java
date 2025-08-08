package com.mercadoBitcoin.livro_contas.DTOs.ativos.request;

import java.math.BigDecimal;

public record CreateAtivoRequest(
        String nomeAtivo,
        BigDecimal valorAtivo,
        Boolean isDisponivelVenda,
        Long contaId
) {
}
