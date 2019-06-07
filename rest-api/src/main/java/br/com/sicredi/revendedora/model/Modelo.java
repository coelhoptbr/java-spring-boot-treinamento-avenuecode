package br.com.sicredi.revendedora.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Modelo {

  @Id @GeneratedValue @NotNull private Long id;

  private String nome;

  @ManyToOne private Marca marca;

  public Modelo() {}

  public Modelo(Long id) {
    this.id = id;
  }

  public Modelo(Long id, String nome, Marca marca) {
    this.id = id;
    this.nome = nome;
    this.marca = marca;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Marca getMarca() {
    return marca;
  }

  public void setMarca(Marca marca) {
    this.marca = marca;
  }
}
