package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaRespostaDTO;
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
    public ResponseEntity<OperacaoDTO> deposito(OperacaoDTO dto) {
        OperacaoDTO resposta = this.service.deposito(new OperacaoDTO(dto.cpf(), dto.valor()));
        return ResponseEntity.ok().body(resposta);
    }

    @PostMapping(path = "/saque")
    public ResponseEntity<OperacaoDTO> saque(OperacaoDTO dto) {
        OperacaoDTO resposta = this.service.saque(new OperacaoDTO(dto.cpf(), dto.valor()));
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/transferencia")
    public ResponseEntity<TransferenciaRespostaDTO> transferencia(TransferenciaDTO dto) {
        TransferenciaRespostaDTO resposta = this.service.transferencia(new TransferenciaDTO(dto.cpf1(), dto.cpf2(), dto.valor()));
        return ResponseEntity.ok(resposta);
    }
}
