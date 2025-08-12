package com.mercadoBitcoin.livro_contas.useCases.ativos;

import com.mercadoBitcoin.livro_contas.exceptions.custom.AtivoNotFoundException;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.ativos.AtivosRepository;
import com.mercadoBitcoin.livro_contas.useCases.contas.ReadContasUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReadAtivoUseCase {

    private AtivosRepository ativosRepository;
    private ReadContasUseCase readContasUseCase;

    public ReadAtivoUseCase(AtivosRepository ativosRepository, ReadContasUseCase readContasUseCase) {
        this.ativosRepository = ativosRepository;
        this.readContasUseCase = readContasUseCase;
    }


    public AtivosEntity findById(Long id) {
        return ativosRepository.findById(id).orElseThrow(() -> new AtivoNotFoundException(id));
    }

    public Page<AtivosEntity> findAllAtivosVenda(Pageable page, Long contaId) {
        log.info("Verifica se a conta existe");
        ContasEntity contasEntity = readContasUseCase.findById(contaId);
        return ativosRepository.findAllAtivosVenda(page, contaId);
    }
}
