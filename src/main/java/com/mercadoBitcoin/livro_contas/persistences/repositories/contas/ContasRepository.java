package com.mercadoBitcoin.livro_contas.persistences.repositories.contas;

import com.mercadoBitcoin.livro_contas.persistences.entities.ContasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasRepository extends JpaRepository<ContasEntity, Long> {
}
