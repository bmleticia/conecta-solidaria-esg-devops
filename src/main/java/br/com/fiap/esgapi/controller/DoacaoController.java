package br.com.fiap.esgapi.controller;

import br.com.fiap.esgapi.dto.DoacaoRequest;
import br.com.fiap.esgapi.dto.DoacaoResponse;
import br.com.fiap.esgapi.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {
    private final DoacaoService service;

    public DoacaoController(DoacaoService service) {
        this.service = service;
    }

    @GetMapping("/pedido/{idPedido}")
    public List<DoacaoResponse> listarPorPedido(@PathVariable Long idPedido) {
        return service.listarPorPedido(idPedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoacaoResponse criar(@RequestBody @Valid DoacaoRequest request) {
        return service.criar(request);
    }
}
