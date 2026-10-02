package br.com.fiap.esgapi.dto;

import jakarta.validation.constraints.*;

public record PedidoAjudaRequest(
        @NotBlank @Size(max = 500) String descricao,
        @NotBlank @Size(max = 100) String localizacao,
        @NotNull Long idTipoNecessidade,
        @NotBlank @Email @Size(max = 100) String emailUsuario
) {}
