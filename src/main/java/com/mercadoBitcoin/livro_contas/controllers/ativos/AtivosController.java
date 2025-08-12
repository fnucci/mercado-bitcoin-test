package com.mercadoBitcoin.livro_contas.controllers.ativos;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.request.CreateAtivoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.ativos.response.AtivosResponse;
import com.mercadoBitcoin.livro_contas.services.ativos.AtivosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ativos")
@Slf4j
public class AtivosController {

    private AtivosService ativosService;

    public AtivosController(AtivosService ativosService) {
        this.ativosService = ativosService;
    }

    @PostMapping
    public ResponseEntity<AtivosResponse> cadastrarAtivo(@RequestBody @Valid CreateAtivoRequest ativoRequest,
                                                         UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando o ativo.");
        AtivosResponse ativoResponse = ativosService.createAtivo(ativoRequest);
        log.info("Conta criada com sucesso!");
        URI uri = uriBuilder.path("/ativos/{id}").buildAndExpand(ativoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(ativoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtivosResponse> findById(@PathVariable Long id) {
        log.info("Buscando o ativo.");
        AtivosResponse ativosResponse = ativosService.findById(id);
        log.info("Ativo recuperado com sucesso.");
        return ResponseEntity.ok(ativosResponse);
    }

    //    @GetMapping("/listarTodos")
//    public Page<AtivosResponse> findAll(
//            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "id") Pageable page) {
//        log.info("Buscando todos os usuarios.");
//        return ativosService.findAll(page);
//    }
//
//    @PutMapping("/comprar/conta/{contaId}/ativo/{id}")
//    public ResponseEntity<ContaResponse> comprarAtivo(@PathVariable Long contaId, @PathVariable Long id, @RequestBody @Valid ComprarAtivoRequest comprarAtivoRequest) {
//        log.info("Cadastrando a conta.");
//        ContaResponse contaResponse = ativosService.comprarAtivo(comprarAtivoRequest, contaId, id);
//        log.info("Credito operado com sucesso!");
//        return ResponseEntity.ok(contaResponse);
//    }
//
    @PutMapping("/{id}")
    public ResponseEntity<AtivosResponse> atualizarAtivo(@PathVariable Long id, @RequestBody @Valid CreateAtivoRequest updateAtivoRequest,
                                                         UriComponentsBuilder uriBuilder) {
        log.info("Atualizando o ativo.");
        AtivosResponse ativosResponse = ativosService.updateAtivo(updateAtivoRequest, id);
        log.info("Debito operado com sucesso!");
        return ResponseEntity.ok(ativosResponse);
    }
}
