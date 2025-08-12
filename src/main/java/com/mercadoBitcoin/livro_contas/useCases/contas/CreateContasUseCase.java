package com.mercadoBitcoin.livro_contas.useCases.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.contas.ContasRepository;
import com.mercadoBitcoin.livro_contas.validations.ContaValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreateContasUseCase {

    private ContasRepository contasRepository;
    List<ContaValidationService> validations;

    public CreateContasUseCase(ContasRepository contasRepository, List<ContaValidationService> validations) {
        this.contasRepository = contasRepository;
        this.validations = validations;
    }

    public ContasEntity createConta(CreateContaRequest contaRequest) {
        log.info("Validando os campos da requisicao");
        this.validations.forEach(v -> v.validate(contaRequest));

        log.info("Criando a conta.");
        ContasEntity conta = new ContasEntity(contaRequest);
        return this.contasRepository.save(conta);
    }
}
