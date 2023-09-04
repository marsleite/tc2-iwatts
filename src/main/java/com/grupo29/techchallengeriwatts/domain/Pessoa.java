package com.grupo29.techchallengeriwatts.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {
    private Long id;
    private String nome;
    private String email;
    private String sexo;
    private LocalDate dataNascimento;
}
