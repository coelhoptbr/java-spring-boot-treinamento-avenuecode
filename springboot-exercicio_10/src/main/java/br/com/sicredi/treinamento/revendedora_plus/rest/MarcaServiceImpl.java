package br.com.sicredi.treinamento.revendedora_plus.rest;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import br.com.sicredi.treinamento.revendedora_plus.repository.MarcaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService {

  private final MarcaRepository marcaRepository;

  public MarcaServiceImpl(MarcaRepository marcaRepository) {
    this.marcaRepository = marcaRepository;
  }

  @Override
  public Marca create(Marca marca) {
    return marcaRepository.save(marca);
  }

  @Override
  public List<Marca> findAll() {
    return marcaRepository.findAll();
  }
}
