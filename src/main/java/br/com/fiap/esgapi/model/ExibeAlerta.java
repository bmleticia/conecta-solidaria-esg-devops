package br.com.fiap.esgapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_EXIBE_ALERTA")
public class ExibeAlerta {
    @EmbeddedId
    private ExibeAlertaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("email")
    @JoinColumn(name = "ds_email")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alertaId")
    @JoinColumn(name = "id_alerta")
    private Alerta alerta;

    public ExibeAlerta() {}
    public ExibeAlertaId getId() { return id; }
    public void setId(ExibeAlertaId id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Alerta getAlerta() { return alerta; }
    public void setAlerta(Alerta alerta) { this.alerta = alerta; }
}
