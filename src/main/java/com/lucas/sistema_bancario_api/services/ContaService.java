package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.repositories.ContaRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public void criarConta(Conta novaConta) {
        this.repository.save(novaConta);
    }

    public BigDecimal consultarSaldo(Conta consulta) {
        return consulta.getSaldo();
    }

    public Conta buscarContaPorCpf(String cpf) {
        return this.repository.findContaByCpf(cpf).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}
