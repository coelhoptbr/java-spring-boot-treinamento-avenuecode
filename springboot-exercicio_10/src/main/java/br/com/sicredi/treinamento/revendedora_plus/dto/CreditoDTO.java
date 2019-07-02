package br.com.sicredi.treinamento.revendedora_plus.dto;

public class CreditoDTO {

    private Boolean positivado;
    private Double valorDivida;

    public Boolean getPositivado() {
        return positivado;
    }

    public void setPositivado(Boolean positivado) {
        this.positivado = positivado;
    }

    public Double getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(Double valorDivida) {
        this.valorDivida = valorDivida;
    }
}
