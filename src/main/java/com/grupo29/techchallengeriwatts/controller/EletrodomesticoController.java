package com.grupo29.techchallengeriwatts.controller;

import com.grupo29.techchallengeriwatts.domain.Eletrodomestico;
import com.grupo29.techchallengeriwatts.domain.Endereco;
import com.grupo29.techchallengeriwatts.dto.EletrodomesticoDTO;
import com.grupo29.techchallengeriwatts.repository.gateway.EletrodomesticoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/iwatts/api/v1/eletrodomestico")
@Slf4j
public class EletrodomesticoController {

    @Autowired
    EletrodomesticoRepository eletrodomesticoRepository;
    @Autowired
    Validator validator;

    @PostMapping("/register")
    public ResponseEntity registerEletrodomestico(@RequestBody EletrodomesticoDTO eletrodomesticoDTO) {
        Map<Path, String> violations = validar(eletrodomesticoDTO);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(violations);
        }

        Eletrodomestico eletrodomestico = eletrodomesticoRepository.createEletrodomestico(eletrodomesticoDTO.toEletrodomestico());
        return ResponseEntity.status(HttpStatus.CREATED).body(eletrodomestico.toResponseDTO());
    }

    @PutMapping("/updateEletrodomestico/{id}")
    public ResponseEntity updateEletrodomestico(@PathVariable Long id, @RequestBody EletrodomesticoDTO eletrodomesticoDTO) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Id não pode ser nulo");
        }
        Map<Path, String> violations = validar(eletrodomesticoDTO);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(violations);
        }
        Eletrodomestico eletrodomestico = eletrodomesticoRepository.updateEletrodomestico(eletrodomesticoDTO.toEletrodomestico());
        return ResponseEntity.ok(eletrodomestico.toResponseDTO());
    }

    @DeleteMapping("/deleteEletrodomestico/{id}")
    public ResponseEntity deleteEndereco(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Id não pode ser nulo");
        }
        eletrodomesticoRepository.deleteEletrodomestico(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/consultaEletrodomestico/{id}")
    public ResponseEntity consultaEletrodomestico(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Id não pode ser nulo");
        }
        Eletrodomestico eletrodomestico = eletrodomesticoRepository.consultaEletrodomestico(id);
        return ResponseEntity.ok(eletrodomestico.toResponseDTO());
    }

    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        return violations.stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
    }



}
