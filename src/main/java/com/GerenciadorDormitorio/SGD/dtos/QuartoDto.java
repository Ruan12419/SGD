package com.GerenciadorDormitorio.SGD.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class QuartoDto {

    @NotBlank
    @Size(max = 5)
    private String quartoNumero;

    private boolean quartoDisponivel;

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

    public String getAlunoOcupanteCPF() {
        return alunoOcupanteCPF;
    }

    public void setAlunoOcupanteCPF(String alunoOcupanteCPF) {
        this.alunoOcupanteCPF = alunoOcupanteCPF;
    }
}
