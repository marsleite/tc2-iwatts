package com.grupo29.techchallengeriwatts.dto;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.utils.FieldUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class PessoaRequestDTO {
    @NotNull(message = "Nome não pode ser vazio")
    private String nome;
    @Email(message = "Formato de email inválido")
    private String email;
    private String sexo;
    @NotNull(message = "Data de nascimento não pode ser vazio")
    private String dataNascimento;

    public Pessoa toDomain() {
        LocalDate dataNascimentoParsed = FieldUtils.convertStringToLocalDate(this.getDataNascimento());
        return Pessoa.builder()
                .nome(nome)
                .email(email)
                .sexo(sexo)
                .dataNascimento(dataNascimentoParsed)
                .build();
    }
}
