package br.com.sicredi.treinamento.revendedora_plus.rest;

import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;
import br.com.sicredi.treinamento.revendedora_plus.repository.ModeloRepository;
import org.springframework.stereotype.Service;

@Service
public class ModeloServiceImpl implements ModeloService {

  private final ModeloRepository modeloRepository;

  public ModeloServiceImpl(ModeloRepository modeloRepository) {
    this.modeloRepository = modeloRepository;
  }

  @Override
  public Modelo findById(Long id) {
    return modeloRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }
}
