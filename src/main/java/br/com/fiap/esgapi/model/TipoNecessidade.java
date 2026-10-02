package br.com.fiap.esgapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TIPO_NECESSIDADE")
public class TipoNecessidade {
    @Id
    @Column(name = "id_tipo_necessidade")
    private Long id;

    @Column(name = "ds_tipo_necessidade", nullable = false, length = 50)
    private String descricao;

    public TipoNecessidade() {}
    public TipoNecessidade(Long id, String descricao) { this.id = id; this.descricao = descricao; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
