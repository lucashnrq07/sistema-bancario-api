package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.entities.Transacao;
import com.lucas.sistema_bancario_api.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransacaoService {

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransacaoRepository repository;

    @Transactional
    public OperacaoDTO deposito(Conta conta, BigDecimal valor) {
        conta.setSaldo(conta.getSaldo().add(valor));
        return new OperacaoDTO(conta.getCpf(), valor);
    }

    @Transactional
    public OperacaoDTO saque(Conta conta, BigDecimal valor) {
        conta.setSaldo(conta.getSaldo().subtract(valor));
        validarSaldo(conta);
        return new OperacaoDTO(conta.getCpf(), valor);
    }

    @Transactional
    public TransferenciaDTO transferencia(Conta c1, Conta c2, BigDecimal valor) {
        saque(c1, valor);
        validarSaldo(c1);
        deposito(c2, valor);

        return new TransferenciaDTO(c1, c2, valor, LocalDateTime.now());
    }

    public void validarSaldo(Conta conta) {
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}
