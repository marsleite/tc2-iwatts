package com.grupo29.techchallengeriwatts.dto;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class PessoaResponseDTO {
    @NotNull(message = "Nome não pode ser vazio")
    private String nome;
    @Email(message = "Formato de email inválido")
    private String email;

    public PessoaResponseDTO fromDomain(Pessoa pessoa) {
        return PessoaResponseDTO.builder()
                .nome(pessoa.getNome())
                .email(pessoa.getEmail())
                .build();
    }
}
