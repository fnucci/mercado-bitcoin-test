package com.mercadoBitcoin.livro_contas.useCases.livros;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.livro.LivroDeOrdem;
import com.mercadoBitcoin.livro_contas.exceptions.custom.SaldoInsuficienteException;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.useCases.ativos.ReadAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.ativos.UpdateAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.contas.ReadContasUseCase;
import com.mercadoBitcoin.livro_contas.useCases.contas.UpdateContasUseCase;
import com.mercadoBitcoin.livro_contas.validations.LivroValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LivroOrdemUseCase {

    private UpdateContasUseCase updateContasUseCase;
    private UpdateAtivoUseCase updateAtivoUseCase;
    private ReadContasUseCase readContasUseCase;
    private ReadAtivoUseCase readAtivoUseCase;
    private List<LivroValidationService> validations;

    public LivroOrdemUseCase(UpdateContasUseCase updateContasUseCase, UpdateAtivoUseCase updateAtivoUseCase, ReadContasUseCase readContasUseCase, ReadAtivoUseCase readAtivoUseCase, List<LivroValidationService> validations) {
        this.updateContasUseCase = updateContasUseCase;
        this.updateAtivoUseCase = updateAtivoUseCase;
        this.readContasUseCase = readContasUseCase;
        this.readAtivoUseCase = readAtivoUseCase;
        this.validations = validations;
    }


    public ContasEntity comprarAtivo(Long contaId, LivroDeOrdem livro) {

        log.info("Executa as validacoes");
        this.validations.forEach(v -> v.validate(livro));

        log.info("Verifica se a conta do comprador existe");
        ContasEntity contaComprador = readContasUseCase.findById(contaId);

        log.info("Verifica se o ativo existe");
        AtivosEntity ativo = readAtivoUseCase.findById(livro.ativoId());

        log.info("Recupera a conta vendedora existe");
        ContasEntity contaVendedor = ativo.getConta();

        log.info("Verifica se o saldo do comprador é suficiente");
        if (contaComprador.getSaldo().compareTo(ativo.getValor()) < 0)
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transacao");

        log.info("efetiva a ordem de compra");
        contaComprador = updateContasUseCase.debitarSaldo(new SaldoRequest(ativo.getValor()), contaComprador.getId());

        log.info("Transfere o saldo para o comprador");
        contaVendedor = updateContasUseCase.creditarSaldo(new SaldoRequest(ativo.getValor()), contaVendedor.getId());

        log.info("Persiste a transferencia do ativo");
        updateAtivoUseCase.transferirAtivo(ativo, contaComprador);

        log.info("Atualiza os saldos de ativos das contas do comprador e vendedor");
        updateContasUseCase.atualizarSaldoAtivos(contaVendedor);
        updateContasUseCase.atualizarSaldoAtivos(contaComprador);

        return readContasUseCase.findById(contaComprador.getId());
    }
}
