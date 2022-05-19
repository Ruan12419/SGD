package com.GerenciadorDormitorio.SGD.controller;

import com.GerenciadorDormitorio.SGD.dtos.QuartoDto;
import com.GerenciadorDormitorio.SGD.models.QuartoModel;
import com.GerenciadorDormitorio.SGD.services.QuartoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/SGD")
public class QuartoController {

    final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping("/BOTQ")
    public ResponseEntity<Object> saveQuartoDorm(@RequestBody @Validated QuartoDto quartoDto) {
        if(conflictSpot(quartoDto)) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Este quarto já está cadastrado!");
        }

        var quartoModel = new QuartoModel();
        BeanUtils.copyProperties(quartoDto, quartoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(quartoService.save(quartoModel));
    }

    @GetMapping("/PEGQ")
    public ResponseEntity<Page<QuartoModel>> getAllQuartos(@PageableDefault(page = 0, size = 20, sort = "quartoNumero", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(quartoService.findAll(pageable));
    }

    @GetMapping("/PEGQ/{alunoOcupanteCPF}")
    public ResponseEntity<Object> getQuarto(@PathVariable(value = "alunoOcupanteCPF") String alunoOcupanteCPF) {
        Optional<QuartoModel> quartoModelOptional = quartoService.findByAlunoOcupanteCPF(alunoOcupanteCPF);
        if(!quartoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(quartoModelOptional.get());
    }

    private boolean conflictSpot(QuartoDto quartoDto) {
        if(quartoService.existsByQuartoNumero(quartoDto.getQuartoNumero())){
            return true;
        }
        return false;
    }
}
