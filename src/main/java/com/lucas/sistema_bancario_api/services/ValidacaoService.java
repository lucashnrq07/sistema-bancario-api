package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.ValidarSaldoDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class ValidacaoService {

    @Autowired
    private ContaService contaService;

    public void validarSaldo(ValidarSaldoDTO dto) {
        Conta conta = this.contaService.buscarContaPorCpf(dto.cpf());
        if (conta.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}
