package com.lucas.sistema_bancario_api.repositories;

import com.lucas.sistema_bancario_api.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
