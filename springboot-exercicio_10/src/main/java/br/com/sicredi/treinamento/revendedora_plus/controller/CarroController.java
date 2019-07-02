package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.CarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.service.CarroService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class CarroController {

  private final CarroService carroService;
  private final ModelMapper modelMapper;

  public CarroController(CarroService carroService, ModelMapper modelMapper) {
    this.carroService = carroService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public List<CarroDTO> getCarros(
      @RequestParam(required = false) String placa, @RequestParam(required = false) Long modeloId) {
    return carroService.findByPlacaAndModeloId(placa, modeloId).stream()
        .map(carro -> modelMapper.map(carro, CarroDTO.class))
        .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Carro carro) throws URISyntaxException {
    Carro createdCarro = carroService.create(carro);
    return ResponseEntity.created(new URI(String.format("/carros/%d", createdCarro.getId())))
        .build();
  }
}
