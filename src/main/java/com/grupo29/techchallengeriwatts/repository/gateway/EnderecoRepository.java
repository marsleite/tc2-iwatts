package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.Endereco;
import com.grupo29.techchallengeriwatts.domain.Pessoa;

import java.util.List;

public interface EnderecoRepository {
    Endereco createAddress(Endereco endereco, List<Pessoa> pessoaId);
    List<Endereco> findEnderecosByPessoa(Long pessoaId);
}
