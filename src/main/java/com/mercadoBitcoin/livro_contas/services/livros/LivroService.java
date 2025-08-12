package com.mercadoBitcoin.livro_contas.services.livros;

import com.mercadoBitcoin.livro_contas.DTOs.contas.response.ContaResponse;
import com.mercadoBitcoin.livro_contas.DTOs.livro.LivroDeOrdem;
import com.mercadoBitcoin.livro_contas.presenters.contas.ContaPresenter;
import com.mercadoBitcoin.livro_contas.useCases.livros.LivroOrdemUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LivroService {

    private LivroOrdemUseCase livroOrdemUseCase;

    public LivroService(LivroOrdemUseCase livroOrdemUseCase) {
        this.livroOrdemUseCase = livroOrdemUseCase;
    }

    public ContaResponse efetuarCompra(Long contaId, LivroDeOrdem livro) {
        return ContaPresenter.toResponse(livroOrdemUseCase.comprarAtivo(contaId, livro));
    }
}
