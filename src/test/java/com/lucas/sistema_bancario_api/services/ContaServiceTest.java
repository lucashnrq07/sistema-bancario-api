package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.CriarContaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.repositories.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContaServiceTest {

    @Mock
    private ContaRepository repository;

    @Autowired
    @InjectMocks
    private ContaService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // ========== CRIAR CONTA ==========
    @Test
    @DisplayName("Conta criada com sucesso")
    void criarContaCaso1() {
        CriarContaDTO novaConta = new CriarContaDTO("00000000001", "Lucas", "Silva");

        service.criarConta(novaConta);

        verify(repository, times(1)).save(any(Conta.class));
    }

    @Test
    @DisplayName("Criar conta usando um CPF já existente")
    void criarContaCaso2() {
        CriarContaDTO novaConta = new CriarContaDTO("00000000001", "Lucas", "Silva");

        when(repository.save(any()))
                .thenThrow(new DataIntegrityViolationException("CPF já cadastrado"));

        assertThrows(DataIntegrityViolationException.class, () -> {
            service.criarConta(novaConta);
        });
    }


    // ========== CONSULTAR SALDO ==========
    @Test
    @DisplayName("Consulta de saldo de uma conta realizado com sucesso")
    void consultarSaldoCaso1() {
        String cpf = "00000000001";
        Conta conta = new Conta(1L, cpf, "Lucas", "Silva", new BigDecimal(100.00));

        when(repository.findContaByCpf(cpf))
                .thenReturn(Optional.of(conta));

        BigDecimal saldo = service.consultarSaldo(cpf);

        assertEquals(new BigDecimal(100.00), saldo);
    }

    @Test
    @DisplayName("Consulta de saldo de uma conta com um CPF não encontrado")
    void consultarSaldoCaso2() {
        when(repository.findContaByCpf("00000000001"))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.consultarSaldo("00000000001");
        });
    }


    // ========== BUSCAR CONTA POR CPF ==========
    @Test
    @DisplayName("Busca de conta através do CPF realizada com sucesso")
    void buscarContaPorCpfCaso1() {
        String cpf = "00000000001";
        Conta conta = new Conta(1L, cpf, "Lucas", "Silva", new BigDecimal(100.00));

        when(repository.findContaByCpf(cpf))
                .thenReturn(Optional.of(conta));

        Conta resultado = service.buscarContaPorCpf(cpf);

        assertNotNull(resultado);
        assertEquals(cpf, resultado.getCpf());
        assertEquals(new BigDecimal(100.00), resultado.getSaldo());
    }

    @Test
    @DisplayName("CPF não encontrado")
    void buscarContaPorCpfCaso2() {
        when(repository.findContaByCpf("00000000001"))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.buscarContaPorCpf("00000000001");
        });
    }
}