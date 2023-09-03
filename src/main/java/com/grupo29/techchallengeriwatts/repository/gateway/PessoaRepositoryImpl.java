package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.dto.PessoaRequestDTO;
import com.grupo29.techchallengeriwatts.dto.PessoaResponseDTO;
import com.grupo29.techchallengeriwatts.exception.RepositoryException;
import com.grupo29.techchallengeriwatts.repository.entity.EnderecoEntity;
import com.grupo29.techchallengeriwatts.repository.entity.PessoaEntity;
import com.grupo29.techchallengeriwatts.repository.gateway.spring.PessoaRepositoryGatewaySpring;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaRepositoryGatewaySpring pessoaRepositoryGatewaySpring;
    private static final String ERRO_PESSOA_JA_EXISTE = "Pessoa já existe";

    public PessoaRepositoryImpl(PessoaRepositoryGatewaySpring pessoaRepositoryGatewaySpring) {
        this.pessoaRepositoryGatewaySpring = pessoaRepositoryGatewaySpring;
    }

    @Override
    public Pessoa createUser(Pessoa pessoa) {
        log.info("Chegou no repository para salvar no banco", pessoa);
        if (pessoaRepositoryGatewaySpring.existsByEmail(pessoa.getEmail())) {
            throw new RepositoryException(ERRO_PESSOA_JA_EXISTE);
        }
        return pessoaRepositoryGatewaySpring.save(
                PessoaEntity.builder()
                        .nome(pessoa.getNome())
                        .email(pessoa.getEmail())
                        .dataNascimento(pessoa.getDataNascimento())
                        .sexo(pessoa.getSexo())
                        .parentesco(pessoa.getParentesco())
                        .build()
        ).toDomain();
    }

    @Override
    public Pessoa getUserById(Long id) {
        return pessoaRepositoryGatewaySpring.findById(id).orElseThrow(() -> new RepositoryException("Pessoa não encontrada")).toDomain();
    }

    @Override
    public void deleteUserById(Long id) {
        pessoaRepositoryGatewaySpring.deleteById(id);
    }

    @Override
    public PessoaResponseDTO updatePessoa(Long pessoaId, PessoaRequestDTO pessoaDTO) {
        PessoaEntity existsPessoa = pessoaRepositoryGatewaySpring.findById(pessoaId)
                .orElseThrow(() -> new RepositoryException("Pessoa não encontrada"));

        return pessoaRepositoryGatewaySpring.save(existsPessoa.fromRequestDTO(pessoaDTO)).toResponseDTO();
    }


}
