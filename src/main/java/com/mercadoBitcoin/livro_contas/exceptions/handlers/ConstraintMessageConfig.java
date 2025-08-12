package com.mercadoBitcoin.livro_contas.exceptions.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstraintMessageConfig {

    @Bean
    public ConstraintMessageResolver constraintMessageResolver() {
        return new DefaultConstraintMessageResolver();
    }

}