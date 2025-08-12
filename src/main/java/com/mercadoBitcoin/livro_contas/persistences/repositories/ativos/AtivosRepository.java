package com.mercadoBitcoin.livro_contas.persistences.repositories.ativos;

import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivosRepository extends JpaRepository<AtivosEntity, Long> {

    @Query("SELECT a FROM ativos as a WHERE isDisponivelVenda = true AND conta.id != :contaId")
    Page<AtivosEntity> findAllAtivosVenda(Pageable page, @Param("contaId") Long contaId);
}
