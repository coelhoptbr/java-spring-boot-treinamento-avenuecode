package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.ClienteDTO;
import br.com.sicredi.treinamento.revendedora_plus.exception.NaoEncontradoException;
import br.com.sicredi.treinamento.revendedora_plus.exception.NaoPositivadoException;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ModelMapper mapper;
    private final ClienteService clienteService;

    public ClienteController(ModelMapper mapper, ClienteService clienteService) {
        this.mapper = mapper;
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> getAll(@RequestParam(required = false, value = "documento") String documento) {
        List<Cliente> lista;
        if (documento == null) {
            lista = clienteService.findAll();
        } else {
            lista = clienteService.findByDocumento(documento);
        }
        return lista.stream()
                .map(carro -> mapper.map(carro, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable(name = "id") Long id) {
        Optional<Cliente> optCli = clienteService.findById(id);

        if (optCli.isPresent()) {
            return ResponseEntity.ok(mapper.map(optCli.get(), ClienteDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cli = mapper.map(clienteDTO, Cliente.class);
        try {
            clienteService.create(cli);
        } catch (NaoPositivadoException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.created(URI.create("/clientes/" + cli.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name="id") Long id) {
        try {
            clienteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(name="id") Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        cliente.setId(id);
        try {
            clienteService.update(cliente);
            return ResponseEntity.noContent().build();
        } catch (NaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
