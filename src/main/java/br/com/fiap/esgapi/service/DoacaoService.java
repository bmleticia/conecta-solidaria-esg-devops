package br.com.fiap.esgapi.service;

import br.com.fiap.esgapi.dto.DoacaoRequest;
import br.com.fiap.esgapi.dto.DoacaoResponse;
import br.com.fiap.esgapi.exception.BusinessException;
import br.com.fiap.esgapi.model.Doacao;
import br.com.fiap.esgapi.model.DoacaoId;
import br.com.fiap.esgapi.repository.DoacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoService {
    private final DoacaoRepository doacaoRepository;
    private final UsuarioService usuarioService;
    private final PedidoAjudaService pedidoAjudaService;
    private final AlertaService alertaService;

    public DoacaoService(DoacaoRepository doacaoRepository, UsuarioService usuarioService,
                         PedidoAjudaService pedidoAjudaService, AlertaService alertaService) {
        this.doacaoRepository = doacaoRepository;
        this.usuarioService = usuarioService;
        this.pedidoAjudaService = pedidoAjudaService;
        this.alertaService = alertaService;
    }

    public List<DoacaoResponse> listarPorPedido(Long idPedido) {
        return doacaoRepository.findByPedido_Id(idPedido).stream().map(this::toResponse).toList();
    }

    @Transactional
    public DoacaoResponse criar(DoacaoRequest request) {
        var id = new DoacaoId(request.emailUsuario(), request.idPedido());
        if (doacaoRepository.existsById(id)) {
            throw new BusinessException("Este usuario ja registrou uma doacao para este pedido.");
        }

        var usuario = usuarioService.buscarEntidade(request.emailUsuario());
        var pedido = pedidoAjudaService.buscarEntidade(request.idPedido());

        var doacao = new Doacao();
        doacao.setId(id);
        doacao.setUsuario(usuario);
        doacao.setPedido(pedido);
        doacao.setMensagem(request.mensagem());

        var salva = doacaoRepository.save(doacao);
        alertaService.criar("O pedido " + request.idPedido() + " recebeu uma nova doacao");

        return toResponse(salva);
    }

    private DoacaoResponse toResponse(Doacao doacao) {
        return new DoacaoResponse(
                doacao.getUsuario().getEmail(),
                doacao.getUsuario().getNome(),
                doacao.getPedido().getId(),
                doacao.getMensagem()
        );
    }
}
