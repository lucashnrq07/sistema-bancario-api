package com.lucas.sistema_bancario_api.controller;

import com.lucas.sistema_bancario_api.dtos.CriarContaDTO;
import com.lucas.sistema_bancario_api.services.ContaService;
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

import java.math.BigDecimal;

@RestController
@RequestMapping("/contas")
@Tag(name = "Contas", description = "Endpoints com operações de uma conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    @Operation(summary = "Criar uma conta", description = "Usuário cria uma nova conta")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CriarContaDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "409", description = "Conta já existente")
    })
    public ResponseEntity<CriarContaDTO> criarConta(@RequestBody @Valid CriarContaDTO dto) {
        CriarContaDTO conta = this.service.criarConta(dto);
        return ResponseEntity.ok(conta);
    }

    @GetMapping(path = "/{cpf}")
    @Operation(summary = "Consultar saldo", description = "Consultar saldo de uma conta através do CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta de saldo realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String cpf) {
        BigDecimal saldo = this.service.consultarSaldo(cpf);
        return ResponseEntity.ok().body(saldo);
    }
}
