package br.com.fiap.esgapi.dto;

public record PedidoAjudaResponse(
        Long id,
        String descricao,
        String localizacao,
        Long idTipoNecessidade,
        String tipoNecessidade,
        String emailUsuario,
        String nomeUsuario
) {}
