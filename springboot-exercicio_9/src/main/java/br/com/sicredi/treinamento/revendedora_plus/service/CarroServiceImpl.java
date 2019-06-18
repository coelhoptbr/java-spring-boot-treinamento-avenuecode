package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.repository.CarroRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CarroServiceImpl implements CarroService {

  private final CarroRepository carroRepository;
  private final FipeService fipeService;
  private final ModeloService modeloService;

  public CarroServiceImpl(
      CarroRepository carroRepository, FipeService fipeService,
      ModeloService modeloService) {
    this.carroRepository = carroRepository;
    this.fipeService = fipeService;
    this.modeloService = modeloService;
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
    carro.setModelo(modeloService.findById(carro.getModelo().getId()));
    carro.setValor(fipeService.getValor(carro.getModelo().getNome(), carro.getAno()));
    return carroRepository.save(carro);
  }
}
