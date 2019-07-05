package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import br.com.sicredi.treinamento.revendedora_plus.service.MarcaService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

  private final MarcaService marcaService;

  public MarcaController(MarcaService marcaService) {
    this.marcaService = marcaService;
  }

  @GetMapping
  public List<Marca> getAll() {
    return marcaService.findAll();
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Marca marca) throws URISyntaxException {
    Marca createdMarca = marcaService.create(marca);
    return ResponseEntity.created(new URI(String.format("/marcas/%d", createdMarca.getId())))
        .build();
  }
}
