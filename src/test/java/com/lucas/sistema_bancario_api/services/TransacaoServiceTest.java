package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.OperacaoRespostaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaRespostaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.entities.Transacao;
import com.lucas.sistema_bancario_api.repositories.TransacaoRepository;
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

class TransacaoServiceTest {

    @Mock
    private ContaService contaService;

    @Mock
    private TransacaoRepository repository;

    @Mock
    private ValidacaoService validacaoService;

    @Autowired
    @InjectMocks
    private TransacaoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // ========== DEPÓSITO ==========
    @Test
    @DisplayName("Depósito em conta existente com valor positivo, atualizando corretamente o saldo")
    void depositoCaso1() {
        Conta conta = new Conta(1L, "00000000001", "Lucas", "Silva", new BigDecimal(50));

        BigDecimal valorDeposito = new BigDecimal(30.00);
        OperacaoDTO dto = new OperacaoDTO(conta.getCpf(),valorDeposito);

        when(this.contaService.buscarContaPorCpf("00000000001")).thenReturn(conta);

        OperacaoRespostaDTO resposta = service.deposito(dto);

        assertEquals(new BigDecimal(80.00), conta.getSaldo());
        assertEquals(conta.getCpf(), resposta.cpf());
        assertEquals(valorDeposito, resposta.valor());
    }

    @Test
    @DisplayName("Depósito em conta inexistente")
    void depositoCaso2() {
        String cpf = "00000000001";
        OperacaoDTO dto = new OperacaoDTO(cpf, new BigDecimal("50.00"));

        when(contaService.buscarContaPorCpf(cpf))
                .thenThrow(new RuntimeException("Conta não encontrada"));

        assertThrows(RuntimeException.class, () -> {
            service.deposito(dto);
        });
    }


    // ========== SAQUE ==========
    @Test
    @DisplayName("Saque em conta existente com saldo pós saque IGUAL OU MAIOR QUE ZERO, atualizando corretamente o saldo")
    void saqueCaso1() {
        Conta conta = new Conta(1L, "00000000001", "Lucas", "Silva", new BigDecimal(50));

        BigDecimal valorSaque = new BigDecimal(30.00);
        OperacaoDTO dto = new OperacaoDTO(conta.getCpf(), valorSaque);

        when(this.contaService.buscarContaPorCpf(conta.getCpf())).thenReturn(conta);

        OperacaoRespostaDTO resposta = service.saque(dto);

        assertEquals(new BigDecimal(20.00), conta.getSaldo());
        assertEquals(conta.getCpf(), resposta.cpf());
        assertEquals(valorSaque, resposta.valor());
    }

    @Test
    @DisplayName("Saque em conta existente, caso o saldo pós saque seja NEGATIVO")
    void saqueCaso2() {
        Conta conta = new Conta(1L, "00000000001", "Lucas", "Silva", new BigDecimal(50));

        BigDecimal valorSaque = new BigDecimal(60.00);
        OperacaoDTO dto = new OperacaoDTO(conta.getCpf(), valorSaque);

        when(this.contaService.buscarContaPorCpf(conta.getCpf())).thenReturn(conta);

        doThrow(IllegalArgumentException.class)
                .when(validacaoService)
                .validarSaldo(any());

        assertThrows(IllegalArgumentException.class, () -> {
            service.saque(dto);
        });
    }


    // ========== TRANSFERÊNCIA ==========
    @Test
    @DisplayName("Transferência realizada com sucesso entre duas contas existentes")
    void transferenciaCaso1() {
        Conta c1 = new Conta(1L, "00000000001", "Lucas", "Silva", new BigDecimal(100.00));
        Conta c2 = new Conta(2L, "00000000002", "Ana", "Santos", new BigDecimal(50.00));
        BigDecimal valor = new BigDecimal(30.00);

        TransferenciaDTO dto = new TransferenciaDTO(c1.getCpf(), c2.getCpf(), valor);

        when(contaService.buscarContaPorCpf(c1.getCpf()))
                .thenReturn(c1);
        when(contaService.buscarContaPorCpf(c2.getCpf()))
                .thenReturn(c2);

        TransferenciaRespostaDTO resposta = service.transferencia(dto);

        assertEquals(new BigDecimal(70.00), c1.getSaldo());
        assertEquals(new BigDecimal(80.00), c2.getSaldo());

        verify(repository, times(1)).save(any(Transacao.class));
    }

    @Test
    @DisplayName("Transferência retornando erro caso a conta de origem não tenha saldo suficiente para fazer a transação")
    void transferenciaCaso2() {
        Conta c1 = new Conta(1L, "00000000001", "Lucas", "Silva", new BigDecimal(100.00));
        Conta c2 = new Conta(2L, "00000000002", "Ana", "Santos", new BigDecimal(50.00));
        BigDecimal valor = new BigDecimal(30.00);

        TransferenciaDTO dto = new TransferenciaDTO(c1.getCpf(), c2.getCpf(), valor);

        when(contaService.buscarContaPorCpf(c1.getCpf()))
                .thenReturn(c1);
        when(contaService.buscarContaPorCpf(c2.getCpf()))
                .thenReturn(c2);
        doThrow(IllegalArgumentException.class)
                .when(validacaoService)
                .validarSaldo(any());

        assertThrows(IllegalArgumentException.class, () -> {
            service.transferencia(dto);
        });

        verify(repository, never()).save(any(Transacao.class));

        assertEquals(new BigDecimal(50.00), c2.getSaldo());
    }
}