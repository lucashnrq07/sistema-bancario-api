package com.lucas.sistema_bancario_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Dados que devem ser informados para realizar uma transferência")
public record TransferenciaDTO(
        @NotBlank
        @Schema(description = "CPF da conta que vai enviar o valor", example = "000000000-01")
        String cpf1,

        @NotBlank
        @Schema(description = "CPF da conta que vai receber o valor", example = "000000000-02")
        String cpf2,

        @NotNull
        @Schema(description = "Valor que vai ser transferido")
        BigDecimal valor
) {}
