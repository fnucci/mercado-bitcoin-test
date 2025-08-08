package com.mercadoBitcoin.livro_contas.DTOs.contas.request;

import java.math.BigDecimal;

public record CreateContaRequest(
      String nome,
      String cpf
) {
}
