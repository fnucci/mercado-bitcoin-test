package com.mercadoBitcoin.livro_contas.persistences.repositories.ativos;

import com.mercadoBitcoin.livro_contas.persistences.entities.AtivosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivosRepository extends JpaRepository<AtivosEntity, Long> {
}
