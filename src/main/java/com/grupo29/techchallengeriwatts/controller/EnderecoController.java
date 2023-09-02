package com.grupo29.techchallengeriwatts.controller;

import com.grupo29.techchallengeriwatts.domain.Endereco;
import com.grupo29.techchallengeriwatts.dto.EnderecoDTO;
import com.grupo29.techchallengeriwatts.repository.entity.PessoaEntity;
import com.grupo29.techchallengeriwatts.repository.gateway.EnderecoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/iwatts/api/v1/endereco")
@Slf4j
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    Validator validator;

    @PostMapping("/register")
    public ResponseEntity registerEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        Map<Path, String> violations = validar(enderecoDTO);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(violations);
        }

        Endereco endereco = enderecoRepository.createAddress(enderecoDTO.toEndereco(), enderecoDTO.getPessoas());
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco.toResponseDTO());
    }

    @GetMapping("/findEnderecosByPessoa/{pessoaId}")
    public ResponseEntity findEnderecosByPessoa(@PathVariable Long pessoaId) {
        if (pessoaId == null) {
            return ResponseEntity.badRequest().body("PessoaId não pode ser nulo");
        }
        List<Endereco> enderecos = enderecoRepository.findEnderecosByPessoa(pessoaId);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/findEnderecoEntitiesByBairro/{bairro}")
    public ResponseEntity findEnderecoEntitiesByBairro(@PathVariable String bairro) {
        if (bairro == null) {
            return ResponseEntity.badRequest().body("Bairro não pode ser nulo");
        }
        List<Endereco> enderecos = enderecoRepository.findEnderecoEntitiesByBairro(bairro);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/findEnderecoEntitiesByCidade/{cidade}")
    public ResponseEntity findEnderecoEntitiesByCidade(@PathVariable String cidade) {
        if (cidade == null) {
            return ResponseEntity.badRequest().body("Cidade não pode ser nulo");
        }
        List<Endereco> enderecos = enderecoRepository.findEnderecoEntitiesByCidade(cidade);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/findEnderecoEntitiesByEstado/{estado}")
    public ResponseEntity findEnderecoEntitiesByEstado(@PathVariable String estado) {
        if (estado == null) {
            return ResponseEntity.badRequest().body("Estado não pode ser nulo");
        }
        List<Endereco> enderecos = enderecoRepository.findEnderecoEntitiesByEstado(estado);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/findEnderecoEntitiesByRua/{rua}")
    public ResponseEntity findEnderecoEntitiesByRua(@PathVariable String rua) {
        if (rua == null) {
            return ResponseEntity.badRequest().body("Rua não pode ser nulo");
        }
        List<Endereco> enderecos = enderecoRepository.findEnderecoEntitiesByRua(rua);
        return ResponseEntity.ok(enderecos);
    }

    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        return violations.stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
    }
}
