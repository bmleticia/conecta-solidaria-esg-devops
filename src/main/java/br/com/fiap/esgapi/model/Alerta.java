package br.com.fiap.esgapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_ALERTAS")
@SequenceGenerator(name = "seq_alerta", sequenceName = "SEQ_ALERTA", allocationSize = 1)
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alerta")
    @Column(name = "id_alerta")
    private Long id;

    @Column(name = "ds_alerta", nullable = false, length = 200)
    private String descricao;

    public Alerta() {}
    public Alerta(String descricao) { this.descricao = descricao; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
