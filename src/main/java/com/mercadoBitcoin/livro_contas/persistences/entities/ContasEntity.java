package com.mercadoBitcoin.livro_contas.persistences.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "contas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
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

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AtivosEntity> ativos = new ArrayList<>();

    public ContasEntity(CreateContaRequest request) {
        this.clientName = request.nome();
        this.cpf = request.cpf();
        this.saldo = new BigDecimal(0);
        this.saldoAtivos = new BigDecimal(0);
    }
}
