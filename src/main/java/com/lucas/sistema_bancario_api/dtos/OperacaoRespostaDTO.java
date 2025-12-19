package com.lucas.sistema_bancario_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Resposta de uma operação")
public record OperacaoRespostaDTO(
        @Schema(description = "CPF", example = "000000000-01")
        String cpf,

        @Schema(description = "Valor da operação", example = "30.00")
        BigDecimal valor,

        @Schema(description = "Momento em que foi realizada a operação", example = "19/01/2025 - 16:22:46")
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
        LocalDateTime moment
) {}