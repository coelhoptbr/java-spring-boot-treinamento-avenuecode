package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.dto.FipeDTO;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FipeServiceImpl implements FipeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FipeServiceImpl.class);

  @Value("${services.fipe.url}")
  private String fipeUrl;

  private final RestTemplate restTemplate;

  public FipeServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public BigDecimal getValor(String modelo, Integer anoModelo) {
    try {
      return restTemplate.getForObject(fipeUrl, FipeDTO.class, modelo, anoModelo).getValor();
    } catch (Exception ex) {
      LOGGER.error("Erro ao chamar Fipe, gravando zero no banco de dados");
    }
    return BigDecimal.ZERO;
  }
}
