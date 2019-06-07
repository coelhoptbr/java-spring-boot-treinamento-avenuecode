package br.com.sicredi.revendedora.repositories;

import br.com.sicredi.revendedora.model.Carro;
import br.com.sicredi.revendedora.model.Modelo;
import org.springframework.data.repository.CrudRepository;

public interface ModeloRepository extends CrudRepository<Modelo, Long> {

}
