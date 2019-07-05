package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.ClienteDTO;
import br.com.sicredi.treinamento.revendedora_plus.dto.ResponseErrorDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.service.ClienteService;
import br.com.sicredi.treinamento.revendedora_plus.types.ClasseSocial;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

  private final ClienteService clienteService;
  private final ModelMapper mapper;

  public ClienteController(ClienteService clienteService, ModelMapper mapper) {
    this.clienteService = clienteService;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> findAll(
      @RequestParam(required = false) ClasseSocial classeSocial) {
    return ResponseEntity.ok(
        clienteService.findAll(classeSocial).stream()
            .map(cliente -> mapper.map(cliente, ClienteDTO.class))
            .collect(Collectors.toList()));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteService.findById(id);

    if (cliente.isPresent()) {
      return ResponseEntity.ok(mapper.map(cliente.get(), ClienteDTO.class));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity create(@Valid @RequestBody ClienteDTO cliente) {
    try {
      Cliente clienteCreated = clienteService.create(mapper.map(cliente, Cliente.class));
      URI location =
          ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/clientes/{id}")
              .buildAndExpand(clienteCreated.getId())
              .toUri();
      return ResponseEntity.created(location).build();

    } catch (IllegalArgumentException e) {
      LOGGER.warn("Erro ao criar Cliente.", e);
      return ResponseEntity.badRequest().body(new ResponseErrorDTO(e.getMessage()));
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteById(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteService.findById(id);

    if (cliente.isPresent()) {
      clienteService.delete(cliente.get());
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity update(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
    Optional<Cliente> clienteExistenteOptional = clienteService.findById(id);
    if (clienteExistenteOptional.isPresent()) {
      cliente.setId(id);
      try {
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();

      } catch (IllegalArgumentException e) {
        LOGGER.warn("Erro ao atualizar Cliente.", e);
        return ResponseEntity.badRequest().body(new ResponseErrorDTO(e.getMessage()));
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
