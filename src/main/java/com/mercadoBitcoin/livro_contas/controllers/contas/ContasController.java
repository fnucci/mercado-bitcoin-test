package com.mercadoBitcoin.livro_contas.controllers.contas;

import com.mercadoBitcoin.livro_contas.DTOs.contas.request.CreateContaRequest;
import com.mercadoBitcoin.livro_contas.DTOs.contas.request.SaldoRequest;
import com.mercadoBitcoin.livro_contas.DTOs.contas.response.ContaResponse;
import com.mercadoBitcoin.livro_contas.services.contas.ContasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("/contas")
@Slf4j
public class ContasController {

    private ContasService contasService;

    public ContasController(ContasService contasService){
        this.contasService = contasService;
    }

    @PostMapping
    public ResponseEntity<ContaResponse> cadastrarConta(@RequestBody @Valid CreateContaRequest contaRequest,
                                                   UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando a conta.");
        ContaResponse contaResponse = contasService.createConta(contaRequest);
        log.info("Conta criada com sucesso!");
        URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(contaResponse.id()).toUri();
        return ResponseEntity.created(uri).body(contaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> findById(@PathVariable Long id) {
        log.info("Buscando o usuario.");
        ContaResponse conta = contasService.findById(id);
        log.info("Usuario recuperado com sucesso.");
        return ResponseEntity.ok(conta);
    }

    @PutMapping("/creditar/{id}")
    public ResponseEntity<ContaResponse> creditarSaldo(@PathVariable Long id, @RequestBody @Valid SaldoRequest creditarSaldoRequest,
                                           UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando a conta.");
        ContaResponse contaResponse = contasService.creditarSaldo(creditarSaldoRequest, id);
        log.info("Credito operado com sucesso!");
        return ResponseEntity.ok(contaResponse);
    }

    @PutMapping("/debitar/{id}")
    public ResponseEntity<ContaResponse> debitarSaldo(@PathVariable Long id, @RequestBody @Valid SaldoRequest debitarSaldoRequest) {
        log.info("Cadastrando a conta.");
        ContaResponse contaResponse = contasService.debitarSaldo(debitarSaldoRequest, id);
        log.info("Debito operado com sucesso!");
        return ResponseEntity.ok(contaResponse);
    }
}
