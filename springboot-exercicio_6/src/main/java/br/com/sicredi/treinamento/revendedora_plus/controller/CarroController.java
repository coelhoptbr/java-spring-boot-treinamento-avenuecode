package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.service.CarroService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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

  public CarroController(CarroService carroService) {
    this.carroService = carroService;
  }

  @GetMapping
  public List<Carro> getCarros(
      @RequestParam(required = false) String placa, @RequestParam(required = false) Long modeloId) {
    return carroService.findByPlacaAndModeloId(placa, modeloId);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Carro carro) throws URISyntaxException {
    Carro createdCarro = carroService.create(carro);
    return ResponseEntity.created(new URI(String.format("/carros/%d", createdCarro.getId())))
        .build();
  }
}
