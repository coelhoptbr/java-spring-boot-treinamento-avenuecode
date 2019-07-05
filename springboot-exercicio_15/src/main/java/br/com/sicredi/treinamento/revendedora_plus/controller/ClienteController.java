package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.ClienteDTO;
import br.com.sicredi.treinamento.revendedora_plus.dto.ResponseErrorDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.service.ClienteService;
import br.com.sicredi.treinamento.revendedora_plus.types.ClasseSocial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
@Api(tags = "Clientes", description = "Operações relacionadas aos clientes")
public class ClienteController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

  private final ClienteService clienteService;
  private final ModelMapper mapper;

  public ClienteController(ClienteService clienteService, ModelMapper mapper) {
    this.clienteService = clienteService;
    this.mapper = mapper;
  }

  @GetMapping
  @ApiOperation(value = "Retorna uma lista de clientes, possibilitando o filtro por classe social")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Lista de clientes retornada com sucesso"),
        @ApiResponse(code = 400, message = "Foi enviada uma classe social inválida"),
        @ApiResponse(
            code = 500,
            message = "Ocorreu um erro inesperado no servidor ao buscar os clientes")
      })
  public ResponseEntity<List<ClienteDTO>> findAll(
      @ApiParam(name = "Classe Social", value = "Classe social do cliente")
          @RequestParam(required = false)
          ClasseSocial classeSocial) {
    return ResponseEntity.ok(
        clienteService.findAll(classeSocial).stream()
            .map(cliente -> mapper.map(cliente, ClienteDTO.class))
            .map(cliente -> mapper.map(cliente, ClienteDTO.class))
            .collect(Collectors.toList()));
  }

  @GetMapping(path = "/{id}")
  @ApiOperation(value = "Retorna um cliente, buscando pelo id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Cliente retornado com sucesso"),
        @ApiResponse(code = 404, message = "Cliente não encontrado"),
        @ApiResponse(
            code = 500,
            message = "Ocorreu um erro inesperado no servidor ao buscar o cliente")
      })
  public ResponseEntity<ClienteDTO> findById(
      @ApiParam(name = "ID do Cliente", value = "ID do cliente", required = true) @PathVariable
          Long id) {
    Optional<Cliente> cliente = clienteService.findById(id);

    if (cliente.isPresent()) {
      return ResponseEntity.ok(mapper.map(cliente.get(), ClienteDTO.class));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Salva um novo cliente")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Cliente criado com sucesso"),
        @ApiResponse(code = 400, message = "Foi enviado um cliente inválido"),
        @ApiResponse(
            code = 500,
            message = "Ocorreu um erro inesperado no servidor ao salvar cliente")
      })
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create(
      @ApiParam(name = "Cliente", value = "Cliente a ser inserido", required = true)
          @Valid
          @RequestBody
          ClienteDTO cliente) {
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
  @ApiOperation(value = "Exclui um cliente")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Cliente excluído com sucesso"),
        @ApiResponse(code = 404, message = "Cliente não encontrado"),
        @ApiResponse(
            code = 500,
            message = "Ocorreu um erro inesperado no servidor ao excluir o cliente")
      })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity deleteById(
      @ApiParam(name = "ID do Cliente", value = "ID do cliente", required = true) @PathVariable
          Long id) {
    Optional<Cliente> cliente = clienteService.findById(id);

    if (cliente.isPresent()) {
      clienteService.delete(cliente.get());
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(path = "/{id}")
  @ApiResponses(
      value = {
        @ApiResponse(code = 204, message = "Cliente atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Foi enviado um cliente inválido"),
        @ApiResponse(code = 404, message = "Cliente não encontrado"),
        @ApiResponse(
            code = 500,
            message = "Ocorreu um erro inesperado no servidor ao atualizar o cliente")
      })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity update(
      @ApiParam(name = "Cliente", value = "Cliente a ser atualizado", required = true)
          @Valid
          @RequestBody
          ClienteDTO clienteDTO,
      @ApiParam(name = "ID do cliente", value = "ID do cliente", required = true) @PathVariable
          Long id) {
    Optional<Cliente> clienteExistenteOptional = clienteService.findById(id);
    if (clienteExistenteOptional.isPresent()) {
      Cliente cliente = mapper.map(clienteDTO, Cliente.class);
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
