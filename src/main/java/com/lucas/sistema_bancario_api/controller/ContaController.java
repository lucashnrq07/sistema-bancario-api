package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.CriarContaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/contas")
@Tag(name = "Contas", description = "Endpoints com operações de uma conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    @Operation(summary = "Criar uma conta", description = "Usuário cria uma nova conta")
    public ResponseEntity<CriarContaDTO> criarConta(CriarContaDTO dto) {
        CriarContaDTO conta = this.service.criarConta(new CriarContaDTO(dto.cpf(), dto.primeiroNome(), dto.ultimoNome()));
        return ResponseEntity.ok(conta);
    }

    @GetMapping(path = "/{cpf}")
    @Operation(summary = "Consultar saldo", description = "Consultar saldo de uma conta através do CPF")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String cpf) {
        BigDecimal saldo = this.service.consultarSaldo(cpf);
        return ResponseEntity.ok().body(saldo);
    }
}
