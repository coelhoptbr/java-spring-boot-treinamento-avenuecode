package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.service.CarroService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class CarroController {

  private final CarroService carroService;

  public CarroController(CarroService carroService) {
    this.carroService = carroService;
  }

  //http://localhost:8080/carros?modelo=2&placa=AAA-1111
  @GetMapping
  public List<Carro> getCarros(
          @RequestParam(required = false, value = "modelo") Long idModelo,
          @RequestParam(required = false) String placa) {
    return carroService.findAllByFilter(idModelo, placa);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Carro carro) throws URISyntaxException {
    Carro createdCarro = carroService.create(carro);
    return ResponseEntity.created(new URI(String.format("/carros/%d", createdCarro.getId())))
        .build();
  }
}
