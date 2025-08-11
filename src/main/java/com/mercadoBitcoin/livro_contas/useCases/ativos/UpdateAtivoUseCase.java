package com.mercadoBitcoin.livro_contas.useCases.ativos;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import com.mercadoBitcoin.livro_contas.exceptions.AtivoUpdateNotPermitException;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.ativos.AtivosRepository;
import com.mercadoBitcoin.livro_contas.useCases.contas.ReadContasUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateAtivoUseCase {

    private AtivosRepository ativosRepository;
    private ReadAtivoUseCase readAtivoUseCase;
    private ReadContasUseCase readContasUseCase;

    public UpdateAtivoUseCase(AtivosRepository ativosRepository, ReadAtivoUseCase readAtivoUseCase, ReadContasUseCase readContasUseCase) {
        this.ativosRepository = ativosRepository;
        this.readAtivoUseCase = readAtivoUseCase;
        this.readContasUseCase = readContasUseCase;
    }

    public AtivosEntity updateAtivo(CreateAtivoRequest ativoRequest, Long id) {
        log.info("Obtem o ativo no banco de dados.");
        AtivosEntity ativoToUpdate = readAtivoUseCase.findById(id);

        log.info("Verifica se a conta vinculada ao ativo existe");
        ContasEntity contasEntity = readContasUseCase.findById(ativoRequest.contaId());

        if (!ativoToUpdate.getConta().equals(contasEntity))
            throw new AtivoUpdateNotPermitException("A alteracao do dono do ativo so e permitida via compra.");

        log.info("Cria o objeto do ativo atualizado");
        ativoToUpdate.setNameAtivo(ativoRequest.nomeAtivo());
        ativoToUpdate.setValor(ativoRequest.valorAtivo());
        ativoToUpdate.setIsDisponivelVenda(ativoRequest.isDisponivelVenda());
        ativoToUpdate.setConta(contasEntity);

        return this.ativosRepository.save(ativoToUpdate);
    }
}
