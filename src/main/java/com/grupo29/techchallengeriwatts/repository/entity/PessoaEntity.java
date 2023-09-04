package com.grupo29.techchallengeriwatts.repository.entity;

import com.grupo29.techchallengeriwatts.domain.Pessoa;
import com.grupo29.techchallengeriwatts.dto.PessoaRequestDTO;
import com.grupo29.techchallengeriwatts.dto.PessoaResponseDTO;
import com.grupo29.techchallengeriwatts.utils.FieldUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private Map<Long, String> parentesco;
    private String sexo;
    private LocalDate dataNascimento;
    @ManyToMany
    @JoinTable(name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private List<EnderecoEntity> enderecos;

    @ManyToMany
    @JoinTable(name = "pessoa_eletrodomestico",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "eletrodomestico_id"))
    private List<EletrodomesticoEntity> eletrodomesticos;

    public Pessoa toDomain() {
        return Pessoa.builder()
                .id(this.getId())
                .nome(this.getNome())
                .email(this.getEmail())
                .sexo(this.getSexo())
                .dataNascimento(this.getDataNascimento())
                .build();
    }

    public PessoaEntity fromRequestDTO(PessoaRequestDTO pessoa) {
        return PessoaEntity.builder()
                .nome(pessoa.getNome())
                .email(pessoa.getEmail())
                .sexo(pessoa.getSexo())
                .dataNascimento(FieldUtils.convertStringToLocalDate(pessoa.getDataNascimento()))
                .build();
    }

    public PessoaResponseDTO toResponseDTO() {
        return PessoaResponseDTO.builder()
                .email(email)
                .nome(nome)
                .build();
    }

}
