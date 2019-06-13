package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;

import java.util.List;

public interface CarroService {

  List<Carro> findAllByFilter(Long idModelo, String placa);

  Carro create(Carro carro);

}
