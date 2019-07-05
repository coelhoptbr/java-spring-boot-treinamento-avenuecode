package br.com.sicredi.treinamento.revendedora_plus.e2e;

import static br.com.sicredi.treinamento.revendedora_plus.util.JsonWriter.writeAsJson;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.sicredi.treinamento.revendedora_plus.factories.ClienteFactory;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
@Sql("classpath:insert_clientes.sql")
public class ClientePositivadoE2E {

  @Autowired private MockMvc mockMvc;

  @Autowired private MockRestServiceServer server;

  private final String NOME = "Henrique";
  private final String DOCUMENTO = "23433322109";
  private final BigDecimal RENDA_FAMILIAR = BigDecimal.valueOf(22_000.00);
  private final String TELEFONE = "(51) 3434-2322";
  private final String ENDERECO = "Rua teste";
  private final String EMAIL = "henrique@teste.com";

  private Cliente cliente =
      ClienteFactory.build(NOME, DOCUMENTO, RENDA_FAMILIAR, TELEFONE, ENDERECO, EMAIL);

  @Test
  public void insereClientePositivado() throws Exception {
    server
        .expect(requestToUriTemplate("https://credito.test.com/situacao/{cpf}", DOCUMENTO))
        .andRespond(
            withSuccess(
                new ClassPathResource("mocks_credito_service/cliente_positivado.json"),
                MediaType.APPLICATION_JSON));

    // insere um cliente positivado
    mockMvc
        .perform(
            post("/clientes").contentType(MediaType.APPLICATION_JSON).content(writeAsJson(cliente)))
        .andExpect(status().isBadRequest());

    // faz a busca pela classe social do cliente
    mockMvc
        .perform(MockMvcRequestBuilders.get("/clientes").param("classeSocial", "A"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));
  }
}
