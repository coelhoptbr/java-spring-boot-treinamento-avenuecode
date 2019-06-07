package br.com.sicredi.revendedora.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Marca {

  @Id @GeneratedValue private Long id;

  private String nome;

  public Marca() {}

  public Marca(Long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Marca(String nome) {
    this.nome = nome;
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
}
