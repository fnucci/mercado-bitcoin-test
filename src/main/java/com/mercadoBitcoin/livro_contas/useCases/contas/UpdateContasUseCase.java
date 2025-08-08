package com.mercadoBitcoin.livro_contas.useCases.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;
import com.mercadoBitcoin.livro_contas.exceptions.InvalidValueException;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.contas.ContasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class UpdateContasUseCase {

    private ContasRepository contasRepository;
    private ReadContasUseCase readContasUseCase;

    public UpdateContasUseCase(ContasRepository contasRepository, ReadContasUseCase readContasUseCase) {
        this.contasRepository = contasRepository;
        this.readContasUseCase = readContasUseCase;
    }

    public ContasEntity creditarSaldo(SaldoRequest saldoRequest, Long id) {

        log.info("Entrou no use case de atualizacao do saldo.");
        log.info("Buscando a conta a ser atualizada.");
        ContasEntity contaToUpdate = readContasUseCase.findById(id);

        log.info("Atualizando o saldo da conta");
        if(saldoRequest.valor().compareTo(BigDecimal.ZERO) > 0)
            contaToUpdate.setSaldo(contaToUpdate.getSaldo().add(saldoRequest.valor()));
        else
            throw new InvalidValueException("O valor informado deve ser maior que zero.");

        return contasRepository.save(contaToUpdate);
    }

    public ContasEntity debitarSaldo(SaldoRequest saldoRequest, Long id) {
        log.info("Entrou no use case de atualizacao do saldo.");
        log.info("Buscando a conta a ser atualizada.");
        ContasEntity contaToUpdate = readContasUseCase.findById(id);

        log.info("Atualizando o saldo da conta");
        if(saldoRequest.valor().compareTo(BigDecimal.ZERO) > 0
                && contaToUpdate.getSaldo().compareTo(saldoRequest.valor()) >= 0) {
            contaToUpdate.setSaldo(contaToUpdate.getSaldo().subtract(saldoRequest.valor()));
        }
        else {
            throw new InvalidValueException("O valor informado deve ser maior que zero ou o saldo na conta e insuficiente para realizar a operacao.");
        }
        log.info("Atualizando o saldo da conta");
        return contasRepository.save(contaToUpdate);
    }
}
