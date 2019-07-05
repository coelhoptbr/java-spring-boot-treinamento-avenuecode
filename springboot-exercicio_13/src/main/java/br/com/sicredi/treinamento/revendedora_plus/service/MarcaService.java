package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import java.util.List;

public interface MarcaService {
  Marca create(Marca marca);

  List<Marca> findAll();
}
