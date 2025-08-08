package com.mercadoBitcoin.livro_contas.persistences.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "ativos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtivosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nameAtivo;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Boolean isDisponivelVenda;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @JsonBackReference
    private ContasEntity conta;

    public AtivosEntity(CreateAtivoRequest request, ContasEntity conta) {
        this.nameAtivo = request.nomeAtivo();
        this.valor = request.valorAtivo();
        this.isDisponivelVenda = request.isDisponivelVenda();
        this.conta = conta;
    }
}
