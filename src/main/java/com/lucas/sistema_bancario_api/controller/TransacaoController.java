package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping(path = "/deposito")
    public ResponseEntity<OperacaoDTO> deposito(Conta conta, BigDecimal valor) {
        OperacaoDTO resposta = this.service.deposito(conta, valor);
        return ResponseEntity.ok().body(resposta);
    }

    @PostMapping(path = "/saque")
    public ResponseEntity<OperacaoDTO> saque(Conta conta, BigDecimal valor) {
        OperacaoDTO resposta = this.service.saque(conta, valor);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/transferencia")
    public ResponseEntity<TransferenciaDTO> transferencia(Conta c1, Conta c2, BigDecimal valor) {
        TransferenciaDTO resposta = this.service.transferencia(c1, c2, valor);
        return ResponseEntity.ok(resposta);
    }
}
