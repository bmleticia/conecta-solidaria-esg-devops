package br.com.fiap.esgapi.controller;

import br.com.fiap.esgapi.dto.PedidoAjudaRequest;
import br.com.fiap.esgapi.dto.PedidoAjudaResponse;
import br.com.fiap.esgapi.service.PedidoAjudaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoAjudaController {
    private final PedidoAjudaService service;

    public PedidoAjudaController(PedidoAjudaService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoAjudaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PedidoAjudaResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoAjudaResponse criar(@RequestBody @Valid PedidoAjudaRequest request) {
        return service.criar(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
