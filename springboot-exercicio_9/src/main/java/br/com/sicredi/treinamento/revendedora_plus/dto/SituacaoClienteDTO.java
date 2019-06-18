package br.com.sicredi.treinamento.revendedora_plus.dto;

import java.math.BigDecimal;

public class SituacaoClienteDTO {
    private boolean positivado;
    private BigDecimal valorDivida;

    public boolean isPositivado() {
        return positivado;
    }

    public void setPositivado(boolean positivado) {
        this.positivado = positivado;
    }

    public BigDecimal getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(BigDecimal valorDivida) {
        this.valorDivida = valorDivida;
    }
}
