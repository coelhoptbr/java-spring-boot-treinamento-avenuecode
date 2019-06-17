package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;

import java.util.Optional;

public interface ModeloService {

    Optional<Modelo> findById(Long id);
}
