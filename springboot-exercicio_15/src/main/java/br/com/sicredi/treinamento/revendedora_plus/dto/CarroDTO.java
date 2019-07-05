package br.com.sicredi.treinamento.revendedora_plus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "Dados relacionados ao recurso Carro")
public class CarroDTO {
  @ApiModelProperty(notes = "ID gerado para o carro")
  private Long id;
  @ApiModelProperty(notes = "placa do carro")
  private String placa;
  @ApiModelProperty(notes = "modelo do carro")
  private String modelo;
  @ApiModelProperty(notes = "marca do carro")
  private String marca;
  @ApiModelProperty(notes = "Valor do carro")
  private BigDecimal valor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
}
