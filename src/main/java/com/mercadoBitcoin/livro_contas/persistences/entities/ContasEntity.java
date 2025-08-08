package com.mercadoBitcoin.livro_contas.persistences.entities;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "contas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String clientName;

    @NotBlank
    private String cpf;

    @NotNull
    private BigDecimal saldo;

    @NotNull
    private BigDecimal saldoAtivos;

    public ContasEntity(CreateContaRequest request) {
        this.clientName = request.nome();
        this.cpf = request.cpf();
        this.saldo = new BigDecimal(0);
        this.saldoAtivos = new BigDecimal(0);
    }
}
