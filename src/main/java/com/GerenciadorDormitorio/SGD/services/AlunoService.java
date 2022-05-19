package com.GerenciadorDormitorio.SGD.services;

import com.GerenciadorDormitorio.SGD.models.AlunoModel;
import com.GerenciadorDormitorio.SGD.repositories.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AlunoService {

    final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public AlunoModel save(AlunoModel alunoModel){
        return alunoRepository.save(alunoModel);
    }

    public boolean existsByAlunoCPF(String alunoCPF){
        return alunoRepository.existsByAlunoCPF(alunoCPF);
    }

    public boolean existsByAlunoNome(String alunoNome){
        return alunoRepository.existsByAlunoNome(alunoNome);
    }

    public Page<AlunoModel> findAll(Pageable pageable){
        return alunoRepository.findAll(pageable);
    }

    public Optional<AlunoModel> findById(String id){
        return alunoRepository.findById(id);
    }

    @Transactional
    public void delete(AlunoModel alunoModel){
        alunoRepository.delete(alunoModel);
    }
}
