package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.dto.CreditoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditoServiceImpl implements CreditoService {

  private final RestTemplate restTemplate;

  @Value("${services.credito.url}")
  private String creditoUrl;

  public CreditoServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public boolean isPositivado(String cpf) {
    CreditoDTO creditoDTO = restTemplate.getForObject(creditoUrl, CreditoDTO.class, cpf);

    return Boolean.TRUE.equals(creditoDTO.getPositivado());
  }
}
