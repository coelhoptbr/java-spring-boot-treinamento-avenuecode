package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.exception.NaoEncontradoException;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    List<Cliente> findByDocumento(String documento);

    void create(Cliente cliente);

    void update(Cliente cliente) throws NaoEncontradoException;

    void deleteById(Long id) throws NaoEncontradoException;
}
