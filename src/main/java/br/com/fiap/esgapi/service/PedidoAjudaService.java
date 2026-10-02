package br.com.fiap.esgapi.service;

import br.com.fiap.esgapi.dto.PedidoAjudaRequest;
import br.com.fiap.esgapi.dto.PedidoAjudaResponse;
import br.com.fiap.esgapi.exception.NotFoundException;
import br.com.fiap.esgapi.model.PedidoAjuda;
import br.com.fiap.esgapi.repository.PedidoAjudaRepository;
import br.com.fiap.esgapi.repository.TipoNecessidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoAjudaService {
    private final PedidoAjudaRepository pedidoRepository;
    private final TipoNecessidadeRepository tipoNecessidadeRepository;
    private final UsuarioService usuarioService;
    private final AlertaService alertaService;

    public PedidoAjudaService(PedidoAjudaRepository pedidoRepository, TipoNecessidadeRepository tipoNecessidadeRepository,
                              UsuarioService usuarioService, AlertaService alertaService) {
        this.pedidoRepository = pedidoRepository;
        this.tipoNecessidadeRepository = tipoNecessidadeRepository;
        this.usuarioService = usuarioService;
        this.alertaService = alertaService;
    }

    public List<PedidoAjudaResponse> listar() {
        return pedidoRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PedidoAjudaResponse buscar(Long id) {
        return toResponse(buscarEntidade(id));
    }

    public PedidoAjuda buscarEntidade(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido de ajuda nao encontrado: " + id));
    }

    @Transactional
    public PedidoAjudaResponse criar(PedidoAjudaRequest request) {
        var tipo = tipoNecessidadeRepository.findById(request.idTipoNecessidade())
                .orElseThrow(() -> new NotFoundException("Tipo de necessidade nao encontrado."));
        var usuario = usuarioService.buscarEntidade(request.emailUsuario());

        var pedido = new PedidoAjuda();
        pedido.setDescricao(request.descricao());
        pedido.setLocalizacao(request.localizacao());
        pedido.setTipoNecessidade(tipo);
        pedido.setUsuario(usuario);

        var salvo = pedidoRepository.save(pedido);
        alertaService.criar("Novo pedido de ajuda cadastrado: Pedido " + salvo.getId());

        long qtd = pedidoRepository.countByTipoNecessidade_Id(tipo.getId());
        if (qtd >= 3) {
            alertaService.criar("Alta demanda identificada para: " + tipo.getDescricao());
        }

        return toResponse(salvo);
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new NotFoundException("Pedido de ajuda nao encontrado: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    private PedidoAjudaResponse toResponse(PedidoAjuda pedido) {
        return new PedidoAjudaResponse(
                pedido.getId(),
                pedido.getDescricao(),
                pedido.getLocalizacao(),
                pedido.getTipoNecessidade().getId(),
                pedido.getTipoNecessidade().getDescricao(),
                pedido.getUsuario().getEmail(),
                pedido.getUsuario().getNome()
        );
    }
}
