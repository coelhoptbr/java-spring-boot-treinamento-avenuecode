package br.com.sicredi.treinamento.revendedora_plus.enumeration;

import java.math.BigDecimal;

public enum ClasseSocialEnum {
    A(BigDecimal.valueOf(18740.01), null),
    B(BigDecimal.valueOf(9370.01), BigDecimal.valueOf(18740)),
    C(BigDecimal.valueOf(3748.01), BigDecimal.valueOf(9370)),
    D(BigDecimal.valueOf(1874.01), BigDecimal.valueOf(3748)),
    E(BigDecimal.valueOf(0), BigDecimal.valueOf(1874));

    ClasseSocialEnum(BigDecimal rendaFamiliarMinima, BigDecimal rendaFamiliarMaxima) {
        this.rendaFamiliarMinima = rendaFamiliarMinima;
        this.rendaFamiliarMaxima = rendaFamiliarMaxima;
    }

    private BigDecimal rendaFamiliarMinima;
    private BigDecimal rendaFamiliarMaxima;

    public BigDecimal getRendaFamiliarMaxima() {
        return this.rendaFamiliarMaxima;
    }

    public BigDecimal getRendaFamiliarMinima() {
        return this.rendaFamiliarMinima;
    }
}
