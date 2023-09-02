package com.grupo29.techchallengeriwatts.repository.gateway.spring;

import com.grupo29.techchallengeriwatts.repository.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecoRepositoryGatewaySpring extends JpaRepository<EnderecoEntity, Long> {

  @Query("SELECT e FROM EnderecoEntity e JOIN e.pessoas p WHERE p.id = :pessoaId")
  List<EnderecoEntity> findEnderecosByPessoaId(@Param("pessoaId") Long pessoaId);

  List<EnderecoEntity> findEnderecoEntitiesByBairro(String bairro);

  List<EnderecoEntity> findEnderecoEntitiesByRua(String rua);

  List<EnderecoEntity> findEnderecoEntitiesByCidade(String cidade);

  List<EnderecoEntity> findEnderecoEntitiesByEstado(String estado);

}
