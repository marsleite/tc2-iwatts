package com.grupo29.techchallengeriwatts.controller;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.dto.PessoaRequestDTO;
import com.grupo29.techchallengeriwatts.exception.HandlerException;
import com.grupo29.techchallengeriwatts.exception.RepositoryException;
import com.grupo29.techchallengeriwatts.repository.gateway.PessoaRepository;
import com.grupo29.techchallengeriwatts.useCase.PessoaUseCase;
import com.grupo29.techchallengeriwatts.utils.FieldUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/iwatts/api/v1/pessoa")
@Slf4j
public class PessoaController {
    private final PessoaUseCase pessoaUseCase;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private Validator validator;

    public PessoaController(PessoaUseCase pessoaUseCase) {
        this.pessoaUseCase = pessoaUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity registerPessoa(@Valid @RequestBody PessoaRequestDTO pessoa) {
        try {
            log.info("Iniciando processo de criação de Pessoa: {}", pessoa);
            Pessoa createdPessoa = pessoaUseCase.executeCreate(pessoa.toDomain());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPessoa);
        } catch (RuntimeException e) {
            HandlerException handlerException = new HandlerException(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(handlerException);
        } catch (Exception e) {

            HandlerException handlerException = new HandlerException(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(handlerException);
        }
    }

    @GetMapping
    public ResponseEntity getPessoa(@RequestParam Long pessoaId) {
        try {
            return ResponseEntity.ok(pessoaRepository.getUserById(pessoaId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getPessoaByEmail(@PathVariable String email) {
        if (email == null)
            return ResponseEntity.badRequest().body("Email não pode ser nulo");

        try {
            return ResponseEntity.ok(pessoaRepository.getUsersByEmail(email));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity getPessoaByNome(@PathVariable String nome) {
        if (nome == null)
            return ResponseEntity.badRequest().body("Nome não pode ser nulo");

        try {
            return ResponseEntity.ok(pessoaRepository.getUsersByNome(nome));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity getPessoaByDataNascimento(@PathVariable String dataNascimento) {
        if (dataNascimento == null)
            return ResponseEntity.badRequest().body("Data de nascimento não pode ser nula");

        try {
            LocalDate dataNascimentoParsed = FieldUtils.convertStringToLocalDate(dataNascimento);
            return ResponseEntity.ok(pessoaRepository.getUsersByDataNascimento(dataNascimentoParsed));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletePessoa(@RequestParam Long pessoaId) {
        try {
            pessoaRepository.deleteUserById(pessoaId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/parentesco")
    public ResponseEntity addParenteToPessoa(@RequestParam Long pessoaId,
                                             @RequestParam Long parenteId,
                                             @RequestParam String parentesco) {
        try {
            pessoaRepository.addParentesco(pessoaId, parenteId, parentesco);
            return ResponseEntity.ok("Parentesco adicionado");
        } catch (RepositoryException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity updatePessoa(@RequestParam Long id, @RequestBody PessoaRequestDTO pessoaDTO) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("Id não pode ser nulo");
            }
            Map<Path, String> violations = validar(pessoaDTO);
            if (!violations.isEmpty()) {
                return ResponseEntity.badRequest().body(violations);
            }
            return ResponseEntity.ok(pessoaRepository.updatePessoa(id, pessoaDTO));
        } catch (RepositoryException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private <T> Map<Path, String> validar(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        return violations.stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
    }
}
