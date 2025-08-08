package com.mercadoBitcoin.livro_contas.useCases.ativos;

import com.mercadoBitcoin.livro_contas.exceptions.AtivoNotFoundException;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.ativos.AtivosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReadAtivoUseCase {

    private AtivosRepository ativosRepository;

    public ReadAtivoUseCase(AtivosRepository ativosRepository) {
        this.ativosRepository = ativosRepository;
    }


    public AtivosEntity findById(Long id) {
        return ativosRepository.findById(id).orElseThrow(() -> new AtivoNotFoundException(id));
    }
}
