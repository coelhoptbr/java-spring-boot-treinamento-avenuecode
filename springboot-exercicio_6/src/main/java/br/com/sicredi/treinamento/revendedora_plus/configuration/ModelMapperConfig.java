package br.com.sicredi.treinamento.revendedora_plus.configuration;

import br.com.sicredi.treinamento.revendedora_plus.dto.InCarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.dto.OutCarroDTO;
import br.com.sicredi.treinamento.revendedora_plus.model.Carro;
import br.com.sicredi.treinamento.revendedora_plus.model.Modelo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mm = new ModelMapper();

        mm.createTypeMap(Carro.class, OutCarroDTO.class)
                .addMapping(c -> c.getModelo().getNome(), OutCarroDTO::setModelo)
                .addMapping(c -> c.getModelo().getMarca().getNome(), OutCarroDTO::setMarca);
/*
        typInCarro = mm.createTypeMap(InCarroDTO.class, Carro.class)
                .addMapping(cDTO -> new Modelo(cDTO.getIdModelo()), Carro::setModelo);
*/
        return mm;
    }
//RestTemplate -> Feign Service
    //WSDO2 -> para consumir servicos SOAP convertendo para REST
    // node como conversor de SOAP para XML
}
