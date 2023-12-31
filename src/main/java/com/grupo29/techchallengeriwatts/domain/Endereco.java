package com.grupo29.techchallengeriwatts.domain;

import com.grupo29.techchallengeriwatts.dto.EnderecoResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Endereco {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
    private String bairro;
    private List<Pessoa> pessoas;
    @EqualsAndHashCode.Exclude
    List<Eletrodomestico> eletrodomesticos;

    public EnderecoResponseDTO toResponseDTO() {
        return new EnderecoResponseDTO(this);
    }
}
