package com.lucas.sistema_bancario_api.repositories;

import com.lucas.sistema_bancario_api.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findById(Long id);
    Optional<Conta> findContaByCpf(String cpf);
}
