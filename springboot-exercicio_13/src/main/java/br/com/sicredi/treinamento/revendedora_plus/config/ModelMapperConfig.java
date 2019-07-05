package br.com.sicredi.treinamento.revendedora_plus.config;

import br.com.sicredi.treinamento.revendedora_plus.dto.CarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.dto.ClienteDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper
        .createTypeMap(Carro.class, CarroDTO.class)
        .addMapping(c -> c.getModelo().getNome(), CarroDTO::setModelo)
        .addMapping(c -> c.getModelo().getMarca().getNome(), CarroDTO::setMarca);

    modelMapper.createTypeMap(ClienteDTO.class, Cliente.class);
    modelMapper.createTypeMap(Cliente.class, ClienteDTO.class);

    return modelMapper;
  }
}
