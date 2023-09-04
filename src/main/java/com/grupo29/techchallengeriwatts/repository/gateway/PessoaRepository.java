package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.dto.PessoaRequestDTO;
import com.grupo29.techchallengeriwatts.dto.PessoaResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface PessoaRepository {

    Pessoa createUser(Pessoa user);

    Pessoa getUserById(Long id);

    List<Pessoa> getUsersByEmail(String email);

    List<Pessoa> getUsersByNome(String nome);

    List<Pessoa> getUsersByDataNascimento(LocalDate dataNascimento);

    void deleteUserById(Long id);

    PessoaResponseDTO updatePessoa(Long pessoaId, PessoaRequestDTO pessoaDTO);

    void addParentesco(Long pessoaId, Long parenteId, String parentesco);
}
