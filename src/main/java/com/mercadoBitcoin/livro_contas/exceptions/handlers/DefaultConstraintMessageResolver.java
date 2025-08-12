package com.mercadoBitcoin.livro_contas.exceptions.handlers;

import java.util.Map;

public class DefaultConstraintMessageResolver implements ConstraintMessageResolver {


    private static final Map<String, String> CONSTRAINT_MESSAGES = Map.of(
            "CONTA", "Conta já está cadastrada",
            "ATIVO", "Ativo já está cadastrado"
    );

    @Override
    public String resolveMessage(String rawMessage) {
        if (rawMessage != null && rawMessage.contains("Unique index or primary key violation")) {
            for (Map.Entry<String, String> entry : CONSTRAINT_MESSAGES.entrySet()) {
                if (rawMessage.contains(entry.getKey())) {
                    return entry.getValue();
                }
            }
        }
        return "Violação de integridade de dados";
    }
}
