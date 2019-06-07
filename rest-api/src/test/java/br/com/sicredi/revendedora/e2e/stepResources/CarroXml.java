package br.com.sicredi.revendedora.e2e.stepResources;

import br.com.sicredi.revendedora.model.Carro;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarroXml {
  private Long id;
  private String placa;

  public CarroXml() {}

  public CarroXml(Carro carro) {
    this.id = carro.getId();
    this.placa = carro.getPlaca();
  }
}
