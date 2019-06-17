package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import java.util.List;
import java.util.Optional;

public interface CarroService {

  List<Carro> findByPlacaAndModeloId(String placa, Long modeloId);

  Carro create(Carro carro);

  Optional<Carro> findById(Long id);
}
