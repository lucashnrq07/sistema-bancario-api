package com.lucas.sistema_bancario_api.repositories;

import com.lucas.sistema_bancario_api.entities.Conta;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findContaByCpf(String cpf);

    boolean existsByCpf(@NotBlank String cpf);
}
