package com.GerenciadorDormitorio.SGD.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_ALUNO_SGD")
public class AlunoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true, length = 13)
    private String alunoCPF;

    @Column(nullable = false, length = 50)
    private String alunoNome;

    @Column(nullable = false, length = 25)
    private String alunoCurso;

    @Column(nullable = false)
    private LocalDateTime registrationDate;


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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
