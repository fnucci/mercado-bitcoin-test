package com.mercadoBitcoin.livro_contas.services.ativos;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.ativos.response.AtivosResponse;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.presenters.ativos.AtivoPresenter;
import com.mercadoBitcoin.livro_contas.useCases.ativos.CreateAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.ativos.ReadAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.ativos.UpdateAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.contas.UpdateContasUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AtivosService {

    private ReadAtivoUseCase readAtivoUseCase;
    private CreateAtivoUseCase createAtivoUseCase;
    private UpdateAtivoUseCase updateAtivoUseCase;
    private UpdateContasUseCase updateContasUseCase;

    public AtivosService(ReadAtivoUseCase readAtivoUseCase, CreateAtivoUseCase createAtivoUseCase, UpdateAtivoUseCase updateAtivoUseCase, UpdateContasUseCase updateContasUseCase) {
        this.createAtivoUseCase = createAtivoUseCase;
        this.readAtivoUseCase = readAtivoUseCase;
        this.updateAtivoUseCase = updateAtivoUseCase;
        this.updateContasUseCase = updateContasUseCase;

    }

    public AtivosResponse createAtivo(CreateAtivoRequest ativoRequest) {
        AtivosEntity ativos = this.createAtivoUseCase.createAtivo(ativoRequest);

        //Atualiza o saldo de ativos da conta
        updateContasUseCase.atualizarSaldoAtivos(ativos.getConta());

        return AtivoPresenter.toResponse(ativos);
    }

    public AtivosResponse findById(Long id) {
        return AtivoPresenter.toResponse(this.readAtivoUseCase.findById(id));
    }

    public AtivosResponse updateAtivo(CreateAtivoRequest ativoRequest, Long id) {

        log.info("Busca se o ativo existe");
        AtivosEntity ativos = this.updateAtivoUseCase.updateAtivo(ativoRequest, id);

        //Atualiza o saldo de ativos da conta
        updateContasUseCase.atualizarSaldoAtivos(ativos.getConta());

        return AtivoPresenter.toResponse(ativos);
    }

    public Page<AtivosResponse> findAll(Pageable page, Long contaId) {

        log.info("Entrou no servico de busca de todos os ativos disponiveis para venda.");
        return readAtivoUseCase.findAllAtivosVenda(page, contaId).map(AtivoPresenter::toResponse);
    }
}
