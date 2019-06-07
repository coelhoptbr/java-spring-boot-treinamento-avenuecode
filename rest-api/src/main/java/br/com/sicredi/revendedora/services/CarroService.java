package br.com.sicredi.revendedora.services;

import br.com.sicredi.revendedora.exception.AlteracaoNaoPermitidaException;
import br.com.sicredi.revendedora.exception.CarroNaoExisteException;
import br.com.sicredi.revendedora.exception.DadoObrigatorioNaoInformadoException;
import br.com.sicredi.revendedora.exception.ModeloNaoExisteException;
import br.com.sicredi.revendedora.model.Carro;
import java.util.List;
import java.util.Optional;

public interface CarroService {
  List<Carro> findAll();

  Carro create(Carro carro) throws AlteracaoNaoPermitidaException, ModeloNaoExisteException, DadoObrigatorioNaoInformadoException;

  Carro update(Carro carro) throws CarroNaoExisteException, ModeloNaoExisteException, DadoObrigatorioNaoInformadoException;

  Carro updatePlaca(Carro carro) throws CarroNaoExisteException, DadoObrigatorioNaoInformadoException;

  void delete(Long id);

  Optional<Carro> findById(Long id);
}
