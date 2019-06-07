package br.com.sicredi.revendedora.e2e.stepResources;

import br.com.sicredi.revendedora.model.Carro;
import br.com.sicredi.revendedora.model.Marca;
import br.com.sicredi.revendedora.model.Modelo;

public class CarroStepResource {
  private Long id;
  private String placa;
  private Long idMarca;
  private String nomeMarca;
  private Long idModelo;
  private String nomeModelo;

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public Long getIdModelo() {
    return idModelo;
  }

  public void setIdModelo(Long idModelo) {
    this.idModelo = idModelo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Carro toCarro() {
    Carro carro = new Carro();
    carro.setId(id);
    carro.setPlaca(placa);

    carro.setModelo(new Modelo(idModelo, nomeModelo, new Marca(idMarca, nomeMarca)));
    return carro;
  }
}
