package br.com.sicredi.treinamento.revendedora_plus.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import br.com.sicredi.treinamento.revendedora_plus.config.RestTemplateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;;

@RunWith(SpringRunner.class)
@RestClientTest(CreditoServiceImpl.class)
@Import(RestTemplateConfig.class)
public class CreditoServiceImplTest {

  @Autowired private MockRestServiceServer server;

  @Autowired private CreditoServiceImpl creditoService;

  private static final String CPF = "23422344322";

  @Test
  public void clientePositivadoTest() {
    server
        .expect(requestToUriTemplate("https://credito.test.com/situacao/{cpf}", CPF))
        .andRespond(
            withSuccess(
                new ClassPathResource("mocks_credito_service/cliente_positivado.json"),
                MediaType.APPLICATION_JSON));

    boolean positivado = creditoService.isPositivado(CPF);

    assertTrue(positivado);
  }

  @Test
  public void clienteNegativadoTest() {
    server
        .expect(requestToUriTemplate("https://credito.test.com/situacao/{cpf}", CPF))
        .andRespond(
            withSuccess(
                new ClassPathResource("mocks_credito_service/cliente_negativado.json"),
                MediaType.APPLICATION_JSON));

    boolean positivado = creditoService.isPositivado(CPF);

    assertFalse(positivado);
  }
}
