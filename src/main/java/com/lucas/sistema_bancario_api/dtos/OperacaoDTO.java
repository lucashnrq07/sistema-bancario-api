package com.lucas.sistema_bancario_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Dados que devem ser informados para realizar uma operação (saque/depósito)")
public record OperacaoDTO(
        @NotBlank
        @Schema(description = "CPF da conta que vai realizar a operação", example = "000000000-01")
        String cpf,

        @Schema(description = "valor da operação", example = "50.00")
        @DecimalMin("0.01")
        BigDecimal valor
) {}
