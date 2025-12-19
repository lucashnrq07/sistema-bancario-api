package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public void criarConta(Conta novaConta) {
        this.repository.save(novaConta);
    }

    public BigDecimal consultarSaldo(String cpf) throws Exception {
        try {
            Conta consulta = buscarContaPorCpf(cpf);
            return consulta.getSaldo();
        } catch (Exception e) {
            throw new Exception("Conta não encontrada");
        }
    }

    public Conta buscarContaPorCpf(String cpf) throws Exception {
        return repository.findContaByCpf(cpf)
                .orElseThrow(() -> new Exception("Conta não encontrada"));
    }
}
