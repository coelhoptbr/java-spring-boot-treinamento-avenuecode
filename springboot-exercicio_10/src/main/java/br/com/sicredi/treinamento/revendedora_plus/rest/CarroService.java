package br.com.sicredi.treinamento.revendedora_plus.rest;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import java.util.List;

public interface CarroService {

  List<Carro> findByPlacaAndModeloId(String placa, Long modeloId);

  Carro create(Carro carro);
}
