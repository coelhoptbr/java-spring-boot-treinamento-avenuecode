package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.repository.CarroRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarroServiceImpl implements CarroService {

  private final CarroRepository carroRepository;

  public CarroServiceImpl(CarroRepository carroRepository) {
    this.carroRepository = carroRepository;
  }

  @Override
  public List<Carro> findAllByFilter(Long idModelo, String placa) {
    if (placa == null) {
      if (idModelo == null) {
        return carroRepository.findAll();
      } else {
        return carroRepository.findAllByModeloId(idModelo);
      }
    } else {
      if (idModelo == null) {
        return carroRepository.findAllByPlaca(placa);
      } else {
        return carroRepository.findAllByModeloIdAndPlaca(idModelo, placa);
      }
    }
  }

  @Override
  public Carro create(Carro carro) {
    return carroRepository.save(carro);
  }

}
