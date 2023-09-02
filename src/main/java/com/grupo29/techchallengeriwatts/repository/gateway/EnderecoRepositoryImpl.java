package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.Endereco;
import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.repository.entity.EnderecoEntity;
import com.grupo29.techchallengeriwatts.repository.entity.PessoaEntity;
import com.grupo29.techchallengeriwatts.repository.gateway.spring.EnderecoRepositoryGatewaySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private final EnderecoRepositoryGatewaySpring enderecoRepositoryGatewaySpring;

    @Autowired
    public EnderecoRepositoryImpl(EnderecoRepositoryGatewaySpring enderecoRepositoryGatewaySpring) {
        this.enderecoRepositoryGatewaySpring = enderecoRepositoryGatewaySpring;
    }

    @Override
    public Endereco createAddress(Endereco endereco, List<Pessoa> pessoas) {
        List<PessoaEntity> pessoasEntities = pessoas.stream()
                .map(pessoa -> PessoaEntity.builder()
                        .id(pessoa.getId())
                        .build())
                .toList();
        return enderecoRepositoryGatewaySpring.save(
                EnderecoEntity.builder()
                        .rua(endereco.getRua())
                        .numero(endereco.getNumero())
                        .cidade(endereco.getCidade())
                        .cep(endereco.getCep())
                        .estado(endereco.getEstado())
                        .pais(endereco.getPais())
                        .bairro(endereco.getBairro())
                        .pessoas(pessoasEntities)
                        .build()
        ).toDomain();
    }

    @Override
    public List<Endereco> findEnderecosByPessoa(Long pessoaId) {
        return enderecoRepositoryGatewaySpring
                .findEnderecosByPessoaId(pessoaId)
                .stream()
                .map(EnderecoEntity::toDomain)
                .toList();
    }
}
