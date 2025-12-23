package com.lucas.sistema_bancario_api.services;

import com.lucas.sistema_bancario_api.dtos.CriarContaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.exceptions.ContaExistenteException;
import com.lucas.sistema_bancario_api.exceptions.ContaNaoEncontradaException;
import com.lucas.sistema_bancario_api.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public CriarContaDTO criarConta(CriarContaDTO dto) {
        if (this.repository.existsByCpf(dto.cpf())) {
            throw new ContaExistenteException("O CPF informado já está cadastrado");
        }

        Conta conta = this.repository.save(new Conta(null, dto.cpf(), dto.primeiroNome(), dto.ultimoNome(), new BigDecimal(0.0)));
        return new CriarContaDTO(dto.cpf(), dto.primeiroNome(), dto.ultimoNome());
    }

    public BigDecimal consultarSaldo(String cpf) {
        try {
            Conta consulta = buscarContaPorCpf(cpf);
            return consulta.getSaldo();
        } catch (Exception e) {
            throw new ContaNaoEncontradaException("Conta não encontrada");
        }
    }

    public Conta buscarContaPorCpf(String cpf) {
        return repository.findContaByCpf(cpf)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }
}
