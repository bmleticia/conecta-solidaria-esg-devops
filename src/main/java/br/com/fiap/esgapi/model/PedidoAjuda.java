package br.com.fiap.esgapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_PEDIDO_AJUDA")
@SequenceGenerator(name = "seq_pedido", sequenceName = "SEQ_PEDIDO_AJUDA", allocationSize = 1)
public class PedidoAjuda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
    @Column(name = "id_pedido")
    private Long id;

    @Column(name = "ds_pedido", nullable = false, length = 500)
    private String descricao;

    @Column(name = "ds_localizacao", nullable = false, length = 100)
    private String localizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_necessidade", nullable = false)
    private TipoNecessidade tipoNecessidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ds_email", nullable = false)
    private Usuario usuario;

    public PedidoAjuda() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public TipoNecessidade getTipoNecessidade() { return tipoNecessidade; }
    public void setTipoNecessidade(TipoNecessidade tipoNecessidade) { this.tipoNecessidade = tipoNecessidade; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
