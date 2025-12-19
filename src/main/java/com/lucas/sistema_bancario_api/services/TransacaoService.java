package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.entities.Transacao;
import com.lucas.sistema_bancario_api.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public void deposito(Conta conta, BigDecimal valor) {
        conta.getSaldo().add(valor);
    }

    @Transactional
    public void saque(Conta conta, BigDecimal valor) throws Exception {
        conta.getSaldo().subtract(valor);
        validarSaldo(conta);
    }

    @Transactional
    public Transacao transferencia(Conta c1, Conta c2, BigDecimal valor) throws Exception {
        saque(c1, valor);
        validarSaldo(c1);
        deposito(c2, valor);

        return new Transacao(null, c1, c2, LocalDateTime.now());
    }

    public void validarSaldo(Conta conta) throws Exception {
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }
}
