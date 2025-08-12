package com.mercadoBitcoin.livro_contas.useCases.ativos;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import com.mercadoBitcoin.livro_contas.persistences.repositories.ativos.AtivosRepository;
import com.mercadoBitcoin.livro_contas.useCases.contas.ReadContasUseCase;
import com.mercadoBitcoin.livro_contas.validations.AtivoValidationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreateAtivoUseCase {

    private AtivosRepository ativosRepository;
    private ReadContasUseCase readContasUseCase;
    List<AtivoValidationService> validations;

    public CreateAtivoUseCase(AtivosRepository ativosRepository, ReadContasUseCase readContasUseCase, List<AtivoValidationService> validations) {
        this.ativosRepository = ativosRepository;
        this.readContasUseCase = readContasUseCase;
        this.validations = validations;
    }

    public AtivosEntity createAtivo(@Valid CreateAtivoRequest ativoRequest) {

        log.info("Executa as validacoes");
        this.validations.forEach(v -> v.validate(ativoRequest));

        log.info("Obtem o dono do ativo.");
        ContasEntity contaEntity = readContasUseCase.findById(ativoRequest.contaId());
        log.info("Cria o objeto do ativo");
        AtivosEntity ativo = new AtivosEntity(ativoRequest, contaEntity);
        return this.ativosRepository.save(ativo);

    }
}
