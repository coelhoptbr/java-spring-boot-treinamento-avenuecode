package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.types.ClasseSocial;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
  List<Cliente> findAll(ClasseSocial classeSocial);

  Optional<Cliente> findById(Long id);

  void delete(Cliente cliente);

  Cliente create(Cliente cliente);

  Cliente update(Cliente cliente);
}
