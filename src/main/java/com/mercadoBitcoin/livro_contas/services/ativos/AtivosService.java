package com.mercadoBitcoin.livro_contas.services.ativos;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.ativos.response.AtivosResponse;
import com.mercadoBitcoin.livro_contas.presenters.ativos.AtivoPresenter;
import com.mercadoBitcoin.livro_contas.useCases.ativos.CreateAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.ativos.ReadAtivoUseCase;
import com.mercadoBitcoin.livro_contas.useCases.ativos.UpdateAtivoUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AtivosService {

    private ReadAtivoUseCase readAtivoUseCase;
    private CreateAtivoUseCase createAtivoUseCase;
    private UpdateAtivoUseCase updateAtivoUseCase;

    public AtivosService(ReadAtivoUseCase readAtivoUseCase, CreateAtivoUseCase createAtivoUseCase, UpdateAtivoUseCase updateAtivoUseCase) {
        this.createAtivoUseCase = createAtivoUseCase;
        this.readAtivoUseCase = readAtivoUseCase;
        this.updateAtivoUseCase = updateAtivoUseCase;

    }

    public AtivosResponse createAtivo(@Valid CreateAtivoRequest ativoRequest) {
        return AtivoPresenter.toResponse(this.createAtivoUseCase.createAtivo(ativoRequest));
    }

    public AtivosResponse findById(Long id) {
        return AtivoPresenter.toResponse(this.readAtivoUseCase.findById(id));
    }
}
