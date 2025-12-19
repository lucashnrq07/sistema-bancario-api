package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.OperacaoRespostaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaRespostaDTO;
import com.lucas.sistema_bancario_api.entities.Conta;
import com.lucas.sistema_bancario_api.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transação", description = "Endpoints com operações que uma conta pode fazer")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping(path = "/deposito")
    @Operation(summary = "Depositar", description = "Depositar um valor em uma conta informada")
    public ResponseEntity<OperacaoRespostaDTO> deposito(OperacaoDTO dto) {
        OperacaoRespostaDTO resposta = this.service.deposito(new OperacaoDTO(dto.cpf(), dto.valor()));
        return ResponseEntity.ok(resposta);
    }

    @PostMapping(path = "/saque")
    @Operation(summary = "Sacar", description = "Sacar um valor de uma conta informada")
    public ResponseEntity<OperacaoRespostaDTO> saque(OperacaoDTO dto) {
        OperacaoRespostaDTO resposta = this.service.saque(new OperacaoDTO(dto.cpf(), dto.valor()));
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/transferencia")
    @Operation(summary = "Transferir", description = "Transferir um valor de uma conta para outra")
    public ResponseEntity<TransferenciaRespostaDTO> transferencia(TransferenciaDTO dto) {
        TransferenciaRespostaDTO resposta = this.service.transferencia(new TransferenciaDTO(dto.cpf1(), dto.cpf2(), dto.valor()));
        return ResponseEntity.ok(resposta);
    }
}
