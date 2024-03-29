package br.com.sicredi.treinamento.revendedora_plus.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  private final RestTemplateBuilder restTemplateBuilder;

  public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  @Bean
  public RestTemplate getRestTemplate() {
    return restTemplateBuilder.build();
  }
}
