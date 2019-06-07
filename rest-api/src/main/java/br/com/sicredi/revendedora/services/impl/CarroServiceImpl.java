package br.com.sicredi.revendedora.services.impl;

import br.com.sicredi.revendedora.exception.AlteracaoNaoPermitidaException;
import br.com.sicredi.revendedora.exception.CarroNaoExisteException;
import br.com.sicredi.revendedora.exception.DadoObrigatorioNaoInformadoException;
import br.com.sicredi.revendedora.exception.ModeloNaoExisteException;
import br.com.sicredi.revendedora.model.Carro;
import br.com.sicredi.revendedora.model.Modelo;
import br.com.sicredi.revendedora.repositories.CarroRepository;
import br.com.sicredi.revendedora.repositories.ModeloRepository;
import br.com.sicredi.revendedora.services.CarroService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CarroServiceImpl implements CarroService {

  Logger logger = LoggerFactory.getLogger(CarroServiceImpl.class);

  private final CarroRepository carroRepository;
  private final ModeloRepository modeloRepository;

  public CarroServiceImpl(CarroRepository carroRepository, ModeloRepository modeloRepository) {
    this.carroRepository = carroRepository;
    this.modeloRepository = modeloRepository;
  }

  @Override
  public List<Carro> findAll() {
    return Lists.newArrayList(carroRepository.findAll());
  }

  @Override
  public Carro create(Carro carro) throws AlteracaoNaoPermitidaException, ModeloNaoExisteException, DadoObrigatorioNaoInformadoException {
    validarDados(carro);
    /*
    if (carro.getId() != null) {
      throw new AlteracaoNaoPermitidaException("Não é possível alterar veículo");
    }
     */
    carro.setId(null);
    Modelo modelo = findModelo(carro);
    carro.setModelo(modelo);
    return carroRepository.save(carro);
  }

  private Modelo findModelo(Carro carro) throws ModeloNaoExisteException {
    if (carro.getModelo() != null && carro.getModelo().getId() != null) {
      Optional<Modelo> optionalModelo = modeloRepository.findById(carro.getModelo().getId());
      if (optionalModelo.isPresent()) {
        return optionalModelo.get();
      }
    }
    throw new ModeloNaoExisteException("Modelo do veículo não encontrado");
  }

  @Override
  public Carro update(Carro carro) throws CarroNaoExisteException, ModeloNaoExisteException, DadoObrigatorioNaoInformadoException {
    if (!carroRepository.findById(carro.getId()).isPresent()) {
      throw new CarroNaoExisteException("ID não encontrado na base de dados");
    }
    validarDados(carro);
    Modelo modelo = findModelo(carro);
    carro.setModelo(modelo);
    return carroRepository.save(carro);
  }

  @Override
  public Carro updatePlaca(Carro carro) throws CarroNaoExisteException, DadoObrigatorioNaoInformadoException {
    Optional<Carro> optCarro = carroRepository.findById(carro.getId());
    if (!optCarro.isPresent()) {
      throw new CarroNaoExisteException("ID não encontrado na base de dados");
    } else {
      validarDados(carro);
      Carro carroSalvar = optCarro.get();
      carroSalvar.setPlaca(carro.getPlaca());
      return carroRepository.save(carroSalvar);
    }
  }

  @Override
  public void delete(Long id) {
    carroRepository.deleteById(id);
  }

  @Override
  public Optional<Carro> findById(Long id) {
    return carroRepository.findById(id);
  }

  private void validarDados(Carro carro) throws DadoObrigatorioNaoInformadoException{
    if (carro.getPlaca().isEmpty()) {
      throw new DadoObrigatorioNaoInformadoException("Placa não informada.");
    }
  }
}
