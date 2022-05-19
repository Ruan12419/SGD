package com.GerenciadorDormitorio.SGD.controller;

import com.GerenciadorDormitorio.SGD.dtos.AlunoDto;
import com.GerenciadorDormitorio.SGD.models.AlunoModel;
import com.GerenciadorDormitorio.SGD.services.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/SGD")
public class AlunoController {

    final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping("/BOT")
    public ResponseEntity<Object> saveAlunoDorm(@RequestBody @Validated AlunoDto alunoDto){
        if(conflictSpot(alunoDto)){
            ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO! O RG do Aluno já está cadastrado no Sistema!");
        }

        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, alunoModel);
        alunoModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoModel));
    }

    @GetMapping("/PEG")
    public ResponseEntity<Page<AlunoModel>> getAllAlunos(@PageableDefault(page = 0, size = 10, sort = "alunoCPF", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll(pageable));
    }

    @GetMapping("/PEG/{id}")
    public ResponseEntity<Object> getAlunoDorm(@PathVariable(value = "id") String id){
        Optional<AlunoModel> alunoModelOptional = alunoService.findById(id);
        if(!alunoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoModelOptional.get());
    }
    private boolean conflictSpot(AlunoDto alunoDto) {
        if(alunoService.existsByAlunoCPF(alunoDto.getAlunoCPF())){
            return true;
        }
        return false;
    }
}
