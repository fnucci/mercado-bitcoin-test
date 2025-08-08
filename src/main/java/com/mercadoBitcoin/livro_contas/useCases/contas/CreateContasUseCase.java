package com.mercadoBitcoin.livro_contas.useCases.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.contas.ContasRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateContasUseCase {

    private ContasRepository contasRepository;

    public CreateContasUseCase(ContasRepository contasRepository) {
        this.contasRepository = contasRepository;
    }

    public ContasEntity createConta(CreateContaRequest contaRequest) {
        ContasEntity conta = new ContasEntity(contaRequest);
        return this.contasRepository.save(conta);
    }
}
