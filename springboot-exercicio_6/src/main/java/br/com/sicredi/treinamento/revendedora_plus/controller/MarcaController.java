package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.OutMarcaDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Marca;
import br.com.sicredi.treinamento.revendedora_plus.service.MarcaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

  private final ModelMapper mapper;
  private final MarcaService marcaService;

  public MarcaController(ModelMapper mapper, MarcaService marcaService) {
    this.mapper = mapper;
    this.marcaService = marcaService;
  }

  @GetMapping
  public List<OutMarcaDTO> getAll() {
    List<Marca> l = marcaService.findAll();
    return l.stream()
            .map(m -> mapper.map(m, OutMarcaDTO.class))
            .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Marca marca) throws URISyntaxException {
    Marca createdMarca = marcaService.create(marca);
    return ResponseEntity.created(new URI(String.format("/marcas/%d", createdMarca.getId())))
        .build();
  }


  @GetMapping("/{id}")
  public OutMarcaDTO getMarcaById(@PathVariable Long id) {
    Marca m = marcaService.findById(id).orElseGet(Marca::new);
    return mapper.map(m, OutMarcaDTO.class);
  }
}
