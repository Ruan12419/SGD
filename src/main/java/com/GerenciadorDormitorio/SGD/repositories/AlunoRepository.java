package com.GerenciadorDormitorio.SGD.repositories;

import com.GerenciadorDormitorio.SGD.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, String> {

    boolean existsByAlunoCPF(String alunoCPF);

    boolean existsByAlunoNome(String alunoNome);

}
