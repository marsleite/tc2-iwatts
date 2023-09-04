package com.grupo29.techchallengeriwatts.repository.gateway.spring;

import com.grupo29.techchallengeriwatts.repository.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PessoaRepositoryGatewaySpring extends JpaRepository<PessoaEntity, Long> {

    @Query("SELECT COUNT(p) > 0 FROM PessoaEntity p WHERE p.email = :email")
    Boolean existsByEmail(@Param("email") String email);

    List<PessoaEntity> findPessoaEntitiesByEmail(String email);

    List<PessoaEntity> findPessoaEntitiesByNome(String nome);

    List<PessoaEntity> findPessoaEntitiesByDataNascimento(LocalDate data);

}
