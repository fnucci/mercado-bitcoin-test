package com.mercadoBitcoin.livro_contas.useCases.contas;

import com.mercadoBitcoin.livro_contas.exceptions.ContaNotFoundException;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.contas.ContasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReadContasUseCase {

    private ContasRepository contasRepository;

    public ReadContasUseCase(ContasRepository contasRepository) {
        this.contasRepository = contasRepository;
    }

    public ContasEntity findById(Long id) {
        return contasRepository.findById(id).orElseThrow(() -> new ContaNotFoundException(id));
    }
}
