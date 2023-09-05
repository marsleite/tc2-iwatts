package com.grupo29.techchallengeriwatts.repository.gateway;

import com.grupo29.techchallengeriwatts.domain.Eletrodomestico;

public interface EletrodomesticoRepository {

  Eletrodomestico createEletrodomestico(Eletrodomestico eletrodomestico);

  void deleteEletrodomestico(Long id);

  Eletrodomestico updateEletrodomestico(Eletrodomestico eletrodomestico);

  Eletrodomestico consultaEletrodomestico(Long id);
}
