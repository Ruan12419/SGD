package com.GerenciadorDormitorio.SGD.models;


import java.time.LocalDateTime;

public class CadastroModel {
    private String alunoCPF;

    private String alunoNome;

    private String alunoCurso;

    private LocalDateTime registrationDate;

    private String quartoNumero;

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

    public String getQuartoNumero() {
        return quartoNumero;
    }

    public void setQuartoNumero(String quartoNumero) {
        this.quartoNumero = quartoNumero;
    }

    public String getAlunoCurso() {
        return alunoCurso;
    }

    public void setAlunoCurso(String alunoCurso) {
        this.alunoCurso = alunoCurso;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
