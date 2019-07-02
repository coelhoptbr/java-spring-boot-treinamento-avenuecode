package br.com.sicredi.treinamento.revendedora_plus.service;

import java.math.BigDecimal;

public interface FipeService {

  BigDecimal getValor(String modelo, Integer anoModelo);
}
