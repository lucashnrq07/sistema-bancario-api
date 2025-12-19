package com.lucas.sistema_bancario_api.config;

import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.Arrays;

public class TestConfig implements CommandLineRunner {

    @Autowired
    private ContaRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Conta conta1 = new Conta(null, "777", "Lucas", "Silva", new BigDecimal(0));
        Conta conta2 = new Conta(null, "444", "Ana", "Santos", new BigDecimal(0));
        repository.saveAll(Arrays.asList(conta1, conta2));
    }
}
