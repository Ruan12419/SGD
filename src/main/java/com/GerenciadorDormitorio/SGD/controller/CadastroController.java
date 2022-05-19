package com.GerenciadorDormitorio.SGD.controller;

import com.GerenciadorDormitorio.SGD.dtos.AlunoDto;
import com.GerenciadorDormitorio.SGD.dtos.QuartoDto;
import com.GerenciadorDormitorio.SGD.models.AlunoModel;
import com.GerenciadorDormitorio.SGD.models.CadastroModel;
import com.GerenciadorDormitorio.SGD.models.QuartoModel;
import com.GerenciadorDormitorio.SGD.services.AlunoService;
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
@CrossOrigin(origins = "*", maxAge = 900)
@RequestMapping("/SGD/cadastro")
public class CadastroController {

    CadastroModel cadastroModel = new CadastroModel();
    final QuartoService quartoService;
    final AlunoService alunoService;

    public CadastroController(QuartoService quartoService, AlunoService alunoService) {
        this.quartoService = quartoService;
        this.alunoService = alunoService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping()
    public ResponseEntity<Object> cadastrarAlunoQuarto(@RequestBody @Validated QuartoDto quartoDto, @RequestBody @Validated AlunoDto alunoDto) {
        if(!conflictSpot(quartoDto, alunoDto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quarto ou aluno já estão cadastrados.");
        }
        quartoDto.setAlunoOcupanteCPF(alunoDto.getAlunoCPF());
        quartoDto.setQuartoDisponivel(false);
        var quartoModel = new QuartoModel();
        BeanUtils.copyProperties(quartoDto, quartoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quarto alugado com sucesso!");
    }

    @GetMapping("/CDSTRD")
    public ResponseEntity<Page<QuartoModel>> getQuartosOcupados(@PageableDefault(page = 0, size = 20, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(quartoService.findByQuartoDisponivel(false, pageable));

    }

    @GetMapping("/PEGQ/{alunoOcupanteCPF}")
    public ResponseEntity<Object> getQuartoEAluno(@PathVariable(value = "alunoOcupanteCPF") String alunoOcupanteCPF) {
        Optional<QuartoModel> quartoModelOptional = quartoService.findByAlunoOcupanteCPF(alunoOcupanteCPF);
        Optional<AlunoModel> alunoModelOptional = alunoService.findById(alunoOcupanteCPF);
        if(!quartoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto não encontrado!");
        } else if (!alunoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }
        AlunoModel alunoModel = alunoModelOptional.get();
        QuartoModel quartoModel = quartoModelOptional.get();

        var cadastroModel = new CadastroModel();
        cadastroModel.setAlunoCPF(alunoModel.getAlunoCPF());
        cadastroModel.setAlunoNome(alunoModel.getAlunoNome());
        cadastroModel.setAlunoCurso(alunoModel.getAlunoCurso());
        cadastroModel.setRegistrationDate(alunoModel.getRegistrationDate());
        cadastroModel.setQuartoNumero(quartoModel.getQuartoNumero());

        return ResponseEntity.status(HttpStatus.OK).body(cadastroModel);
    }

    @GetMapping("/PEGQ")
    public ResponseEntity<Object> getAllQuartoEAluno(@PageableDefault(page = 0, size = 20, direction = Sort.Direction.ASC) Pageable pageable) {

        var alunoModel = alunoService.findAll(pageable);
        var quartoModel = quartoService.findAll(pageable);

        String[] alunoCPF = new String[(int) alunoModel.getTotalElements()];
        for (int i = 0; i < (int) alunoModel.getTotalElements(); i++) {
            alunoCPF[i] = String.valueOf(alunoModel.getContent());
        }

        var cadastroModel = new CadastroModel();
        /*cadastroModel.setAlunoCPF(alunoModel.getAlunoCPF());
        cadastroModel.setAlunoNome(alunoModel.getAlunoNome());
        cadastroModel.setAlunoCurso(alunoModel.getAlunoCurso());
        cadastroModel.setRegistrationDate(alunoModel.getRegistrationDate());
        cadastroModel.setQuartoNumero(quartoModel.getQuartoNumero());*/

        return ResponseEntity.status(HttpStatus.OK).body(alunoCPF);
    }

    /*@PutMapping(value = "{quartoNumero}")
    @Transactional
    public ResponseEntity<Object> putQuarto(QuartoDto quartoDto) {

    }*/

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuarto(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted succesfully!");
    }*/

    //@RequestMapping(value = "cadastrar/{quartoNumero}", method = RequestMethod.PUT)
    @PutMapping("cadastrar")
    public ResponseEntity<Object> atualizaQuarto(@RequestParam String quartoNumero, @RequestParam String alunoCPF ) {

        var quartoDto = quartoService.findById(quartoNumero);
        var quartoModel = new QuartoModel();
        quartoModel.setAlunoOcupanteCPF(alunoCPF);
        quartoModel.setQuartoDisponivel(false);
        BeanUtils.copyProperties(quartoDto, quartoModel);
        quartoModel.setQuartoNumero(quartoNumero);
        return ResponseEntity.status(HttpStatus.OK).body(quartoService.save(quartoModel));
    }

    //@RequestMapping(value = "/descadastro/{alunoCPF}", method = RequestMethod.PUT)
    @PutMapping("/descadastro/")
    public ResponseEntity<Object> desocupaQuarto(@RequestParam String alunoCPF, @RequestParam String quartoNumero ) {

        var quartoDto = quartoService.findById(quartoNumero);
        var quartoModel = new QuartoModel();
        BeanUtils.copyProperties(quartoDto, quartoModel);
        quartoModel.setAlunoOcupanteCPF(null);
        quartoModel.setQuartoDisponivel(true);
        quartoModel.setQuartoNumero(quartoNumero);
        return ResponseEntity.status(HttpStatus.OK).body(quartoService.save(quartoModel));
    }

    private boolean conflictSpot(QuartoDto quartoDto, AlunoDto alunoDto) {
        return quartoService.existsByQuartoNumero(quartoDto.getQuartoNumero()) && alunoService.existsByAlunoCPF(alunoDto.getAlunoCPF());
    }
}
