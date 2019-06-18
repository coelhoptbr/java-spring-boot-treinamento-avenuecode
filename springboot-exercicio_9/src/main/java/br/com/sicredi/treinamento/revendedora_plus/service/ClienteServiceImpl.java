package br.com.sicredi.treinamento.revendedora_plus.service;

import br.com.sicredi.treinamento.revendedora_plus.dto.SituacaoClienteDTO;
import br.com.sicredi.treinamento.revendedora_plus.exception.NaoEncontradoException;
import br.com.sicredi.treinamento.revendedora_plus.exception.NaoPositivadoException;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final RestTemplate restTemplate;
    private final ClienteRepository clienteRepository;
    private final String urlServicoSituacao;

    public ClienteServiceImpl(
            RestTemplate restTemplate,
            ClienteRepository clienteRepository,
            @Value("${services.credito.url}") String urlServicoSituacao) {
        this.restTemplate = restTemplate;
        this.clienteRepository = clienteRepository;
        this.urlServicoSituacao = urlServicoSituacao;
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
    public void create(Cliente cliente) throws NaoPositivadoException {
        cliente.setId(null);

        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("cpf", cliente.getDocumento()
                        .replace("-", "")
                        .replace(".", ""));

        SituacaoClienteDTO situacaoDTO = restTemplate.getForObject(
                urlServicoSituacao,
                SituacaoClienteDTO.class,
                mapParams);

        if (situacaoDTO.isPositivado()) {
            clienteRepository.save(cliente);
        } else {
            throw new NaoPositivadoException("Pessoa física negativada.");
        }
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
