package com.mercadoBitcoin.livro_contas.useCases.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;
import com.mercadoBitcoin.livro_contas.exceptions.custom.InvalidValueException;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.contas.ContasRepository;
import com.mercadoBitcoin.livro_contas.validations.SaldoValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class UpdateContasUseCase {

    private ContasRepository contasRepository;
    private ReadContasUseCase readContasUseCase;
    private List<SaldoValidationService> validations;

    public UpdateContasUseCase(ContasRepository contasRepository, ReadContasUseCase readContasUseCase, List<SaldoValidationService> validations) {
        this.contasRepository = contasRepository;
        this.readContasUseCase = readContasUseCase;
        this.validations = validations;
    }

    public ContasEntity creditarSaldo(SaldoRequest saldoRequest, Long id) {

        log.info("Entrou no use case de atualizacao do saldo.");
        log.info("Executa as validacoes.");
        this.validations.forEach(v -> v.validate(saldoRequest));

        log.info("Buscando a conta a ser atualizada.");
        ContasEntity contaToUpdate = readContasUseCase.findById(id);

        log.info("Atualizando o saldo da conta");
        if (saldoRequest.valor().compareTo(BigDecimal.ZERO) > 0)
            contaToUpdate.setSaldo(contaToUpdate.getSaldo().add(saldoRequest.valor()));
        else
            throw new InvalidValueException("O valor informado deve ser maior que zero.");

        return contasRepository.save(contaToUpdate);
    }

    public ContasEntity debitarSaldo(SaldoRequest saldoRequest, Long id) {
        log.info("Entrou no use case de atualizacao do saldo.");
        log.info("Executa as validacoes.");
        this.validations.forEach(v -> v.validate(saldoRequest));
        log.info("Buscando a conta a ser atualizada.");
        ContasEntity contaToUpdate = readContasUseCase.findById(id);

        log.info("Atualizando o saldo da conta");
        if (saldoRequest.valor().compareTo(BigDecimal.ZERO) > 0
                && contaToUpdate.getSaldo().compareTo(saldoRequest.valor()) >= 0) {
            contaToUpdate.setSaldo(contaToUpdate.getSaldo().subtract(saldoRequest.valor()));
        } else {
            throw new InvalidValueException("O valor informado deve ser maior que zero ou o saldo na conta e insuficiente para realizar a operacao.");
        }
        log.info("Atualizando o saldo da conta");
        return contasRepository.save(contaToUpdate);
    }

    public void atualizarSaldoAtivos(ContasEntity contas) {
        BigDecimal saldoAtivos = BigDecimal.ZERO;
        contas = readContasUseCase.findById(contas.getId());
        for (AtivosEntity ativosEntity : contas.getAtivos())
            saldoAtivos = saldoAtivos.add(ativosEntity.getValor());
        contas.setSaldoAtivos(saldoAtivos);
        contasRepository.save(contas);
    }
}
