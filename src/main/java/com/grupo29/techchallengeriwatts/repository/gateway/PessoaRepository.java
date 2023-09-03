package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.dto.PessoaRequestDTO;
import com.grupo29.techchallengeriwatts.dto.PessoaResponseDTO;

public interface PessoaRepository {

    Pessoa createUser(Pessoa user);

    Pessoa getUserById(Long id);

    void deleteUserById(Long id);

    PessoaResponseDTO updatePessoa(Long pessoaId, PessoaRequestDTO pessoaDTO);
}
