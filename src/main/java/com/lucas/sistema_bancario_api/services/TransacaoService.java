package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaRespostaDTO;
import com.lucas.sistema_bancario_api.dtos.ValidarSaldoDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
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
    public OperacaoDTO deposito(OperacaoDTO dto) {
        Conta conta = this.contaService.buscarContaPorCpf(dto.cpf());
        conta.setSaldo(conta.getSaldo().add(dto.valor()));
        return new OperacaoDTO(conta.getCpf(), dto.valor());
    }

    @Transactional
    public OperacaoDTO saque(OperacaoDTO dto) {
        Conta conta = this.contaService.buscarContaPorCpf(dto.cpf());
        conta.setSaldo(conta.getSaldo().subtract(dto.valor()));
        validarSaldo(new ValidarSaldoDTO(dto.cpf()));
        return new OperacaoDTO(conta.getCpf(), dto.valor());
    }

    @Transactional
    public TransferenciaRespostaDTO transferencia(TransferenciaDTO dto) {
        saque(new OperacaoDTO(dto.cpf1(), dto.valor()));
        validarSaldo(new ValidarSaldoDTO(dto.cpf1()));
        deposito(new OperacaoDTO(dto.cpf2(), dto.valor()));

        return new TransferenciaRespostaDTO(dto.cpf1(), dto.cpf2(), dto.valor(), LocalDateTime.now());
    }

    public void validarSaldo(ValidarSaldoDTO dto) {
        Conta conta = this.contaService.buscarContaPorCpf(dto.cpf());
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}
