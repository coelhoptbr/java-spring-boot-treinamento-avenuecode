package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.repository.CarroRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CarroServiceImpl implements CarroService {

  private final CarroRepository carroRepository;

  public CarroServiceImpl(CarroRepository carroRepository) {
    this.carroRepository = carroRepository;
  }

  @Override
  public List<Carro> findByPlacaAndModeloId(String placa, Long modeloId) {
    if (!StringUtils.isEmpty(placa) && modeloId != null) {
      return carroRepository.findByPlacaAndModeloId(placa, modeloId);
    }

    if (!StringUtils.isEmpty(placa)) {
      return carroRepository.findByPlaca(placa);
    }

    if (modeloId != null) {
      return carroRepository.findByModeloId(modeloId);
    }

    return carroRepository.findAll();
  }

  @Override
  public Carro create(Carro carro) {
    return carroRepository.save(carro);
  }
}
