package com.GerenciadorDormitorio.SGD.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_QUARTO_SGD")
public class QuartoModel implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true, length = 5)
    private String quartoNumero;

    @Column(nullable = false)
    private boolean quartoDisponivel;

    public String getAlunoOcupanteCPF() {
        return alunoOcupanteCPF;
    }

    public void setAlunoOcupanteCPF(String alunoOcupanteCPF) {
        this.alunoOcupanteCPF = alunoOcupanteCPF;
    }

    @Column(nullable = true)
    private String alunoOcupanteCPF;


    public String getQuartoNumero() {
        return quartoNumero;
    }

    public void setQuartoNumero(String quartoNumero) {
        this.quartoNumero = quartoNumero;
    }

    public boolean isQuartoDisponivel() {
        return quartoDisponivel;
    }

    public void setQuartoDisponivel(boolean quartoDisponivel) {
        this.quartoDisponivel = quartoDisponivel;
    }

}
