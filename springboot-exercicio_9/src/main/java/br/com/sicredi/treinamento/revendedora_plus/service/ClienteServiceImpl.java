package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.exception.NaoEncontradoException;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> findByDocumento(String documento) {
        return clienteRepository.findAllByDocumento(documento);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void create(Cliente cliente) {
        cliente.setId(null);
        clienteRepository.save(cliente);
    }

    @Override
    public void update(Cliente cliente) throws NaoEncontradoException {
        Optional<Cliente> optCliente = clienteRepository.findById(cliente.getId());

        if (optCliente.isPresent()) {
            clienteRepository.save(cliente);
        } else {
            throw new NaoEncontradoException("Cliente não encontrado.");
        }
    }

    @Override
    public void deleteById(Long id) throws NaoEncontradoException {
        Optional<Cliente> optCliente = clienteRepository.findById(id);

        if (optCliente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new NaoEncontradoException("Cliente não encontrado.");
        }
    }
}
