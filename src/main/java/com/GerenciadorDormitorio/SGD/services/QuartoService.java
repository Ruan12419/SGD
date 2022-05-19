package com.GerenciadorDormitorio.SGD.services;

import com.GerenciadorDormitorio.SGD.models.QuartoModel;
import com.GerenciadorDormitorio.SGD.repositories.QuartoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class QuartoService {

    final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @Transactional
    public QuartoModel save(QuartoModel quartoModel) {
        return quartoRepository.save(quartoModel);
    }

    @Transactional
    public void delete(QuartoModel quartoModel) {
        quartoRepository.delete(quartoModel);
    }

    public boolean existsByQuartoNumero(String quartoNumero) {
        return quartoRepository.existsByQuartoNumero(quartoNumero);
    }

    public Page<QuartoModel> findAll(Pageable pageable) {
        return quartoRepository.findAll(pageable);
    }

    public Optional<QuartoModel> findById(String id) {
        return quartoRepository.findById(id);
    }

    public Page<QuartoModel> findByQuartoDisponivel(boolean quartoDisponivel, Pageable pageable) {
        return quartoRepository.findByQuartoDisponivel(quartoDisponivel, pageable);
    }

    public Optional<QuartoModel> findByAlunoOcupanteCPF(String alunoOcupanteCPF) {
        return quartoRepository.findByAlunoOcupanteCPF(alunoOcupanteCPF);
    }

}
