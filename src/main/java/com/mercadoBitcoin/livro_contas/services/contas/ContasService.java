package com.mercadoBitcoin.livro_contas.services.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.contas.response.ContaResponse;
import com.mercadoBitcoin.livro_contas.exceptions.ContaNotFoundException;
import com.mercadoBitcoin.livro_contas.presenters.contas.ContaPresenter;
import com.mercadoBitcoin.livro_contas.useCases.contas.CreateContasUseCase;
import com.mercadoBitcoin.livro_contas.useCases.contas.ReadContasUseCase;
import com.mercadoBitcoin.livro_contas.useCases.contas.UpdateContasUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContasService {

    private CreateContasUseCase createContasUseCase;
    private ReadContasUseCase readContasUseCase;
    private UpdateContasUseCase updateContasUseCase;

    public ContasService(CreateContasUseCase createContasUseCase, ReadContasUseCase readContasUseCase, UpdateContasUseCase updateContasUseCase) {
        this.createContasUseCase = createContasUseCase;
        this.readContasUseCase = readContasUseCase;
        this.updateContasUseCase = updateContasUseCase;
    }

    public ContaResponse createConta(CreateContaRequest contaRequest) {
        return ContaPresenter.toResponse(this.createContasUseCase.createConta(contaRequest));
    }

    public ContaResponse findById(Long id) {
        return ContaPresenter.toResponse(this.readContasUseCase.findById(id));
    }

    public ContaResponse creditarSaldo(SaldoRequest saldoRequest, Long id) {
        return ContaPresenter.toResponse(this.updateContasUseCase.creditarSaldo(saldoRequest, id));
    }

    public ContaResponse debitarSaldo(SaldoRequest saldoRequest, Long id) {
        return ContaPresenter.toResponse(this.updateContasUseCase.debitarSaldo(saldoRequest, id));
    }

}
