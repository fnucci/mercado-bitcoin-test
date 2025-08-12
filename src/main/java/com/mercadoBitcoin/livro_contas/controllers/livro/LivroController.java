package com.mercadoBitcoin.livro_contas.controllers.livro;

import com.mercadoBitcoin.livro_contas.DTOs.ativos.response.AtivosResponse;
import com.mercadoBitcoin.livro_contas.DTOs.contas.response.ContaResponse;
import com.mercadoBitcoin.livro_contas.DTOs.livro.LivroDeOrdem;
import com.mercadoBitcoin.livro_contas.services.ativos.AtivosService;
import com.mercadoBitcoin.livro_contas.services.livros.LivroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/livro")
public class LivroController {

    private AtivosService ativosService;
    private LivroService livroService;

    public LivroController(AtivosService ativosService, LivroService livroService) {
        this.ativosService = ativosService;
        this.livroService = livroService;
    }

    @GetMapping("/listarTodos/conta/{contaId}")
    public Page<AtivosResponse> findAllAtivosVenda(
            @PathVariable Long contaId,
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "id") Pageable page) {
        log.info("Buscando todos os ativos.");
        return ativosService.findAll(page, contaId);
    }

    @PutMapping("/comprarAtivo/conta/{contaId}")
    public ResponseEntity<ContaResponse> comprarAtivo(@PathVariable Long contaId, @Valid @RequestBody LivroDeOrdem livro) {
        log.info("Efetuando a transferencia do Ativo.");
        return ResponseEntity.ok(livroService.efetuarCompra(contaId, livro));
    }
}
