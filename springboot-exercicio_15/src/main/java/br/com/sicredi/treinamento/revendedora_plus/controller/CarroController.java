package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.dto.CarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.service.CarroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "Carros", description = "Operações relacionadas aos carros")
public class CarroController {

  private final CarroService carroService;
  private final ModelMapper modelMapper;

  public CarroController(CarroService carroService, ModelMapper modelMapper) {
    this.carroService = carroService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  @ApiOperation(value = "Retorna uma lista de carros, possibilitando o filtro por modelo ou placa")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Lista de carros retornada com sucesso"),
        @ApiResponse(code = 400, message = "Foi enviado um id de modelo com tipo inválido"),
        @ApiResponse(
            code = 500,
            message = "Ocorreu um erro inesperado no servidor ao buscar os carros")
      })
  public List<CarroDTO> getCarros(
      @ApiParam(name = "Placa", value = "Placa do carro para ser utilizada como filtro")
          @RequestParam(required = false)
          String placa,
      @ApiParam(name = "ID do Modelo", value = "ID do modelo para ser utilizado como filtro")
          @RequestParam(required = false)
          Long modeloId) {
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
