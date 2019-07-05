package br.com.sicredi.treinamento.revendedora_plus.config;

import br.com.sicredi.treinamento.revendedora_plus.service.CreditoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(CreditoService.class)
@Import(RestTemplateConfig.class)
public class CreditoClientTest {

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void getPositivadoPositivo() {
        server.expect(requestTo("http://credito-api-homolog.herokuapp.com/situacao/123"))
                .andRespond(withSuccess(new ClassPathResource("cliente_negativado.json"),
                        MediaType.APPLICATION_JSON));

        Boolean situacao = creditoService.isPositivado("123");

        assertEquals(false, situacao);
    }

    @Test
    public void getPositivadoNegativo() {
        server.expect(requestTo("http://credito-api-homolog.herokuapp.com/situacao/321"))
                .andRespond(withSuccess(new ClassPathResource("cliente_positivado.json"),
                        MediaType.APPLICATION_JSON));

        Boolean situacao = creditoService.isPositivado("321");

        assertEquals(true, situacao);
    }
}
