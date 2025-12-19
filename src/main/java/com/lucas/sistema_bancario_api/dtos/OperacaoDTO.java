package com.lucas.sistema_bancario_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Dados que devem ser informados para realizar uma operação (saque/depósito)")
public record OperacaoDTO(
        @NotBlank
        @Schema(description = "CPF da conta que vai receber a operação", example = "000000000-01")
        String cpf,

        @Schema(description = "valor da operação", example = "50.00")
        BigDecimal valor
) {}
