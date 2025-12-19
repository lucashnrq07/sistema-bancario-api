package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    public ResponseEntity<Conta> criarConta(Conta conta) {
        this.service.criarConta(conta);
        return ResponseEntity.ok().body(conta);
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String cpf) {
        Conta conta = service.buscarContaPorCpf(cpf);
        BigDecimal saldo = this.service.consultarSaldo(conta);
        return ResponseEntity.ok().body(saldo);
    }
}
