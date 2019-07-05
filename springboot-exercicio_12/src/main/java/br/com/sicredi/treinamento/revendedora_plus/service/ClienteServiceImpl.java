package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.repository.ClienteRepository;
import br.com.sicredi.treinamento.revendedora_plus.types.ClasseSocial;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

  private final ClienteRepository clienteRepository;
  private final CreditoService creditoService;

  public ClienteServiceImpl(ClienteRepository clienteRepository, CreditoService creditoService) {
    this.clienteRepository = clienteRepository;
    this.creditoService = creditoService;
  }

  @Override
  public List<Cliente> findAll(ClasseSocial classeSocial) {
    if (classeSocial != null) {
      if (classeSocial.getValorMinimo() != null && classeSocial.getValorMaximo() != null) {
        return clienteRepository.findByRendaFamiliarGreaterThanEqualAndRendaFamiliarLessThanEqual(
            classeSocial.getValorMinimo(), classeSocial.getValorMaximo());
      }
      if (classeSocial.getValorMaximo() != null) {
        return clienteRepository.findByRendaFamiliarLessThanEqual(classeSocial.getValorMaximo());
      }
      if (classeSocial.getValorMinimo() != null) {
        return clienteRepository.findByRendaFamiliarGreaterThanEqual(classeSocial.getValorMinimo());
      }
    }
    return Lists.newArrayList(clienteRepository.findAll());
  }

  @Override
  public Optional<Cliente> findById(Long id) {
    return clienteRepository.findById(id);
  }

  @Override
  public void delete(Cliente cliente) {
    clienteRepository.delete(cliente);
  }

  @Override
  public Cliente create(Cliente cliente) {
    verificaCredito(cliente);
    return clienteRepository.save(cliente);
  }

  private void verificaCredito(Cliente cliente) {
    try {
      if (creditoService.isPositivado(cliente.getDocumento())) {
        throw new IllegalArgumentException(
            "Cliente positivado. Não foi possível realizar o cadastro.");
      }
    } catch (Exception e) {
      LOGGER.error("Erro ao obter status", e);
      throw new IllegalArgumentException(
          "Não foi possível verificar a sitação do cliente. Verifique se o CPF está correto.");
    }
  }

  @Override
  public Cliente update(Cliente cliente) {
    return clienteRepository.save(cliente);
  }
}
