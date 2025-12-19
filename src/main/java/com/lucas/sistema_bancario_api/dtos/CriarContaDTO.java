package com.lucas.sistema_bancario_api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados que devem ser informados para criar uma conta")
public record CriarContaDTO(
        @NotBlank
        @Schema(description = "CPF", example = "000000000-01")
        String cpf,

        @NotBlank
        @Schema(description = "Primeiro nome da pessoa", example = "Maria")
        String primeiroNome,

        @NotBlank
        @Schema(description = "Último nome da pessoa", example = "Silva")
        String ultimoNome
) { }
