package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.*;
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

    @Autowired
    private ValidacaoService validacaoService;

    @Transactional
    public OperacaoRespostaDTO deposito(OperacaoDTO dto) {
        Conta conta = this.contaService.buscarContaPorCpf(dto.cpf());
        conta.setSaldo(conta.getSaldo().add(dto.valor()));
        return new OperacaoRespostaDTO(conta.getCpf(), dto.valor(), LocalDateTime.now());
    }

    @Transactional
    public OperacaoRespostaDTO saque(OperacaoDTO dto) {
        Conta conta = this.contaService.buscarContaPorCpf(dto.cpf());
        conta.setSaldo(conta.getSaldo().subtract(dto.valor()));
        validacaoService.validarSaldo(new ValidarSaldoDTO(dto.cpf()));
        return new OperacaoRespostaDTO(conta.getCpf(), dto.valor(), LocalDateTime.now());
    }

    @Transactional
    public TransferenciaRespostaDTO transferencia(TransferenciaDTO dto) {
        saque(new OperacaoDTO(dto.cpf1(), dto.valor()));
        validacaoService.validarSaldo(new ValidarSaldoDTO(dto.cpf1()));
        deposito(new OperacaoDTO(dto.cpf2(), dto.valor()));

        Conta c1 = this.contaService.buscarContaPorCpf(dto.cpf1());
        Conta c2 = this.contaService.buscarContaPorCpf(dto.cpf2());
        this.repository.save(new Transacao(null, c1, c2, dto.valor(), LocalDateTime.now()));

        return new TransferenciaRespostaDTO(dto.cpf1(), dto.cpf2(), dto.valor(), LocalDateTime.now());
    }
}
