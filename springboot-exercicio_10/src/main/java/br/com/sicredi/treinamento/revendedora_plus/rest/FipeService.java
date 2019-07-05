package br.com.sicredi.treinamento.revendedora_plus.rest;

import java.math.BigDecimal;

public interface FipeService {

  BigDecimal getValor(String modelo, Integer anoModelo);
}
