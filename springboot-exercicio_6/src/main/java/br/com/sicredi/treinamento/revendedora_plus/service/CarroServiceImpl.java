package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.dto.OutValorFIPEDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;
import br.com.sicredi.treinamento.revendedora_plus.repository.CarroRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class CarroServiceImpl implements CarroService {

  private final RestTemplate restTemplate;
  private final CarroRepository carroRepository;
  private final String URLServicoValor = "http://172.22.101.33:8090/fipe";

  public CarroServiceImpl(RestTemplate restTemplate,
                          CarroRepository carroRepository) {
    this.restTemplate = restTemplate;
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
  public Optional<Carro> findById(Long id) {
    return carroRepository.findById(id);
  }

  @Override
  public Carro create(Carro carro) {
    Map<String, String> mapParams = new HashMap<String, String>();

    mapParams.put("nomeModelo", carro.getModelo().getNome());
    mapParams.put("ano", carro.getAno().toString());

    OutValorFIPEDTO outVal = restTemplate.getForObject(
            URLServicoValor + "/{nomeModelo}/{ano}",
            OutValorFIPEDTO.class,
            mapParams);

    carro.setValor(outVal.getValor());

    return carroRepository.save(carro);
  }
}
