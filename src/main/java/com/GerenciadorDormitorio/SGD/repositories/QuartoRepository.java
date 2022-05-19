package com.GerenciadorDormitorio.SGD.repositories;

import com.GerenciadorDormitorio.SGD.models.QuartoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<QuartoModel, String> {

    boolean existsByQuartoNumero(String quartoNumero);

    Page<QuartoModel> findByQuartoDisponivel(boolean quartoDisponivel, Pageable pageable);

    Optional<QuartoModel> findByAlunoOcupanteCPF(String alunoOcupanteCPF);

}
