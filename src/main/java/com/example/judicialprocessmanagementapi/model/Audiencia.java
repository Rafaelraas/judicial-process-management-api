package com.example.judicialprocessmanagementapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Audiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoAudiencia tipoAudiencia;

    private String local;

    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoAudiencia getTipoAudiencia() {
        return tipoAudiencia;
    }

    public void setTipoAudiencia(TipoAudiencia tipoAudiencia) {
        this.tipoAudiencia = tipoAudiencia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
