package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.InCarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.dto.OutCarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;
import br.com.sicredi.treinamento.revendedora_plus.service.CarroService;
import br.com.sicredi.treinamento.revendedora_plus.service.ModeloService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carros")
public class CarroController {

  private final ModelMapper mapper;
  private final CarroService carroService;
  private final ModeloService modeloService;

  public CarroController(ModelMapper mapper,
                         CarroService carroService,
                         ModeloService modeloService) {
    this.mapper = mapper;
    this.carroService = carroService;
    this.modeloService = modeloService;
  }

  @GetMapping("/{id}")
  public OutCarroDTO getCarroById(@PathVariable Long id) {
    Carro c = carroService.findById(id).orElseGet(Carro::new);
    return mapper.map(c, OutCarroDTO.class);
  }

  @GetMapping
  public List<OutCarroDTO> getCarros(
      @RequestParam(required = false) String placa, @RequestParam(required = false) Long modeloId) {
    List<Carro> l = carroService.findByPlacaAndModeloId(placa, modeloId);
    return l.stream()
            .map(c -> mapper.map(c, OutCarroDTO.class))
            .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity create(@RequestBody InCarroDTO inCarroDTO) throws URISyntaxException {
    Carro carro = mapper.map(inCarroDTO, Carro.class);

    carro.setModelo(modeloService.findById(inCarroDTO.getIdModelo()).get());

    carro = carroService.create(carro);

    return ResponseEntity.created(new URI(String.format("/carros/%d", carro.getId())))
        .build();
  }
}
