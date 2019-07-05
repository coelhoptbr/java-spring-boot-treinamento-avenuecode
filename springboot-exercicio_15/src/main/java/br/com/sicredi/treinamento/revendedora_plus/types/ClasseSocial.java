package br.com.sicredi.treinamento.revendedora_plus.types;

import java.math.BigDecimal;

public enum ClasseSocial {
  A(new BigDecimal("18740.01"), null),
  B(new BigDecimal("9370.01"), new BigDecimal("18740.00")),
  C(new BigDecimal("3748.01"), new BigDecimal("9370.00")),
  D(new BigDecimal("1874.01"), new BigDecimal("3748.00")),
  E(null, new BigDecimal("1874.00"));

  private final BigDecimal valorMinimo;
  private final BigDecimal valorMaximo;

  ClasseSocial(BigDecimal valorMinimo, BigDecimal valorMaximo) {
    this.valorMinimo = valorMinimo;
    this.valorMaximo = valorMaximo;
  }

  public BigDecimal getValorMinimo() {
    return valorMinimo;
  }

  public BigDecimal getValorMaximo() {
    return valorMaximo;
  }
}
