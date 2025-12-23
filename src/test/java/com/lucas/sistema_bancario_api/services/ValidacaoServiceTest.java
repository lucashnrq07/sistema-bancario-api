package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.ValidarSaldoDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidacaoServiceTest {

    @Mock
    private ContaService contaService;

    @Autowired
    @InjectMocks
    ValidacaoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Validação de saldo com saldo POSITIVO")
    void validarSaldoCasoSucesso() {
        String cpf = "00000000001";
        Conta conta = new Conta(1L, cpf, "Lucas", "Silva", new BigDecimal("50.00"));

        when(contaService.buscarContaPorCpf(cpf))
                .thenReturn(conta);

        assertDoesNotThrow(() -> {
            service.validarSaldo(new ValidarSaldoDTO(cpf));
        });
    }

    @Test
    @DisplayName("Validação de saldo com saldo NEGATIVO")
    void validarSaldoCasoErro() {
        String cpf = "00000000001";
        Conta conta = new Conta(1L, cpf, "Lucas", "Silva", new BigDecimal("-10.00"));

        when(contaService.buscarContaPorCpf(cpf))
                .thenReturn(conta);

        assertThrows(IllegalArgumentException.class, () -> {
            service.validarSaldo(new ValidarSaldoDTO(cpf));
        });
    }


}