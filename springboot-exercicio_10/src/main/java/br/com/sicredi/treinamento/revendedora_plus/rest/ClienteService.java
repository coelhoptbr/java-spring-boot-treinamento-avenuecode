package br.com.sicredi.treinamento.revendedora_plus.rest;

import br.com.sicredi.treinamento.revendedora_plus.enumeration.ClasseSocialEnum;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll(ClasseSocialEnum classeSocial);

    Optional<Cliente> findById(Long id);

    void delete(Cliente cliente);

    Cliente create(Cliente cliente);

    Cliente update(Cliente cliente);
}
