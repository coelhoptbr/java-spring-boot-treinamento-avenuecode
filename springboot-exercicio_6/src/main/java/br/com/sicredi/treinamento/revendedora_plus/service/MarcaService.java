package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.dto.OutMarcaDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import java.util.List;
import java.util.Optional;

public interface MarcaService {
  Marca create(Marca marca);

  List<Marca> findAll();

  Optional<Marca> findById(Long id);
}
