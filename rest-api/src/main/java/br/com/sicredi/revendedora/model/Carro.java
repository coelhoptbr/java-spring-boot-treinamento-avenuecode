package br.com.sicredi.revendedora.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Carro {

  @Id @GeneratedValue private Long id;

  @NotEmpty private String placa;

  @ManyToOne @NotNull @Valid private Modelo modelo;

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

  public Modelo getModelo() {
    return modelo;
  }

  public void setModelo(Modelo modelo) {
    this.modelo = modelo;
  }
}
