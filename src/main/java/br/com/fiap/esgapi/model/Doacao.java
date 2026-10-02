package br.com.fiap.esgapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_DOACAO")
public class Doacao {
    @EmbeddedId
    private DoacaoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("email")
    @JoinColumn(name = "ds_email")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoId")
    @JoinColumn(name = "id_pedido")
    private PedidoAjuda pedido;

    @Column(name = "ds_mensagem", length = 500)
    private String mensagem;

    public Doacao() {}
    public DoacaoId getId() { return id; }
    public void setId(DoacaoId id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public PedidoAjuda getPedido() { return pedido; }
    public void setPedido(PedidoAjuda pedido) { this.pedido = pedido; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
