package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.CriarContaDTO;
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
    public ResponseEntity<Conta> criarConta(CriarContaDTO dto) {
        Conta conta = this.service.criarConta(new CriarContaDTO(dto.cpf(), dto.primeiroNome(), dto.ultimoNome()));
        return ResponseEntity.ok();
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String cpf) {
        BigDecimal saldo = this.service.consultarSaldo(cpf);
        return ResponseEntity.ok().body(saldo);
    }
}
