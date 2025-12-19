package com.lucas.sistema_bancario_api.repositories;

import com.lucas.sistema_bancario_api.dtos.CriarContaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
class ContaRepositoryTest {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private EntityManager manager;

    @Test
    @DisplayName("Caso a conta EXISTA no DB")
    void findContaByCpfCaso1() {
        String cpf = "000000000-01";
        CriarContaDTO dto = new CriarContaDTO(cpf, "Lucas", "Silva");
        this.criarConta(dto);

        Optional<Conta> conta = this.repository.findContaByCpf(cpf);
        assertThat(conta.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Caso a conta NÃO EXISTA no DB")
    void findContaByCpfCaso2() {
        String cpf = "000000000-01";

        Optional<Conta> conta = this.repository.findContaByCpf(cpf);
        assertThat(conta.isEmpty()).isTrue();
    }

    private Conta criarConta(CriarContaDTO dto) {
        Conta conta = new Conta(dto);
        this.manager.persist(conta);
        return conta;
    }
}