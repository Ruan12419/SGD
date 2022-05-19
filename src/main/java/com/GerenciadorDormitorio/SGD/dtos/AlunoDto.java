package com.GerenciadorDormitorio.SGD.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AlunoDto {

    @NotBlank
    @Size(max = 15)
    private String alunoCPF;

    @NotBlank
    private String alunoNome;

    @NotBlank
    private String alunoCurso;


    public String getAlunoCPF() {
        return alunoCPF;
    }

    public void setAlunoCPF(String alunoCPF) {
        this.alunoCPF = alunoCPF;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoCurso() {
        return alunoCurso;
    }

    public void setAlunoCurso(String alunoCurso) {
        this.alunoCurso = alunoCurso;
    }

}
