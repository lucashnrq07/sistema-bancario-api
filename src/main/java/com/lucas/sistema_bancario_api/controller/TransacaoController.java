package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.OperacaoDTO;
import com.lucas.sistema_bancario_api.dtos.OperacaoRespostaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaDTO;
import com.lucas.sistema_bancario_api.dtos.TransferenciaRespostaDTO;
import com.lucas.sistema_bancario_api.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Endpoints com operações financeiras")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/deposito")
    @Operation(summary = "Depositar", description = "Depositar um valor em uma conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Depósito realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OperacaoRespostaDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<OperacaoRespostaDTO> deposito(@RequestBody @Valid OperacaoDTO dto) {
        OperacaoRespostaDTO resposta = service.deposito(dto);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/saque")
    @Operation(summary = "Sacar", description = "Sacar um valor de uma conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Saque realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OperacaoRespostaDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente")
    })
    public ResponseEntity<OperacaoRespostaDTO> saque(@RequestBody @Valid OperacaoDTO dto) {
        OperacaoRespostaDTO resposta = service.saque(dto);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/transferencia")
    @Operation(summary = "Transferir", description = "Transferir um valor entre contas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Transferência realizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransferenciaRespostaDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente ou dados inválidos")
    })
    public ResponseEntity<TransferenciaRespostaDTO> transferencia(@RequestBody @Valid TransferenciaDTO dto) {
        TransferenciaRespostaDTO resposta = service.transferencia(dto);
        return ResponseEntity.ok(resposta);
    }
}
