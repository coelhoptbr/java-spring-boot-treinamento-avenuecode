package br.com.sicredi.treinamento.revendedora_plus.config;

import br.com.sicredi.treinamento.revendedora_plus.rest.FipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(FipeService.class)
@Import(RestTemplateConfig.class)
public class FipeClientTest {

    @Autowired
    private FipeService fipeService;

    @Autowired
    private MockRestServiceServer server;


    @Test
    public void getValorTest() {
        server.expect(requestTo("http://test.fipeService.com/fipe/corsa/2019"))
                .andRespond(withSuccess(new ClassPathResource("fipe_corsa_2019.json"),
                        MediaType.APPLICATION_JSON));

        BigDecimal valor = fipeService.getValor("corsa", 2019);

        assertEquals(new BigDecimal("30500"), valor);
    }
}
