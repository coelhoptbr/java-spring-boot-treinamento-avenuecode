package br.com.sicredi.treinamento.revendedora_plus.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.sicredi.treinamento.revendedora_plus.config.ModelMapperConfig;
import br.com.sicredi.treinamento.revendedora_plus.factories.ClienteFactory;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.service.ClienteService;
import br.com.sicredi.treinamento.revendedora_plus.types.ClasseSocial;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
@Import(ModelMapperConfig.class)
public class ClienteControllerIT {

  @MockBean private ClienteService clienteService;

  @Autowired private MockMvc mockMvc;

  private final String EXPECTED_NOME_1 = "Henrique";
  private final String EXPECTED_NOME_2 = "Sergio";
  private final String EXPECTED_DOCUMENTO_1 = "211235";
  private final String EXPECTED_DOCUMENTO_2 = "234234";
  private final BigDecimal EXPECTED_RENDA_FAMILIAR_1 = BigDecimal.valueOf(2000);
  private final BigDecimal EXPECTED_RENDA_FAMILIAR_2 = BigDecimal.valueOf(100000);
  private final String EXPECTED_ENDERECO_1 = "Rua teste1";
  private final String EXPECTED_ENDERECO_2 = "Rua teste2";
  private final String EXPECTED_TELEFONE_1 = "(51) 234234-3333";
  private final String EXPECTED_TELEFONE_2 = "(11) 23343-3423";
  private final String EXPECTED_EMAIL_1 = "henrique@teste.com";
  private final String EXPECTED_EMAIL_2 = "sergio@sicredi.com.br";
  private final ClasseSocial classeSocial = ClasseSocial.A;

  private List<Cliente> clientes =
      Arrays.asList(
          ClienteFactory.build(
              EXPECTED_NOME_1,
              EXPECTED_DOCUMENTO_1,
              EXPECTED_RENDA_FAMILIAR_1,
              EXPECTED_TELEFONE_1,
              EXPECTED_ENDERECO_1,
              EXPECTED_EMAIL_1),
          ClienteFactory.build(
              EXPECTED_NOME_2,
              EXPECTED_DOCUMENTO_2,
              EXPECTED_RENDA_FAMILIAR_2,
              EXPECTED_TELEFONE_2,
              EXPECTED_ENDERECO_2,
              EXPECTED_EMAIL_2));

  @Test
  public void findClientesByClasseSocialTest() throws Exception {
    when(clienteService.findAll(classeSocial)).thenReturn(clientes);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/clientes").param("classeSocial", "A"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nome").value(EXPECTED_NOME_1))
        .andExpect(jsonPath("$[1].nome").value(EXPECTED_NOME_2))
        .andExpect(jsonPath("$[0].documento").value(EXPECTED_DOCUMENTO_1))
        .andExpect(jsonPath("$[1].documento").value(EXPECTED_DOCUMENTO_2))
        .andExpect(jsonPath("$[0].rendaFamiliar").value(EXPECTED_RENDA_FAMILIAR_1))
        .andExpect(jsonPath("$[1].rendaFamiliar").value(EXPECTED_RENDA_FAMILIAR_2))
        .andExpect(jsonPath("$[0].telefone").value(EXPECTED_TELEFONE_1))
        .andExpect(jsonPath("$[1].telefone").value(EXPECTED_TELEFONE_2))
        .andExpect(jsonPath("$[0].endereco").value(EXPECTED_ENDERECO_1))
        .andExpect(jsonPath("$[1].endereco").value(EXPECTED_ENDERECO_2))
        .andExpect(jsonPath("$[0].email").value(EXPECTED_EMAIL_1))
        .andExpect(jsonPath("$[1].email").value(EXPECTED_EMAIL_2));

    verify(clienteService).findAll(classeSocial);
  }

  @Test
  public void findClientesSemEnviarClasseSocialTest() throws Exception {
    when(clienteService.findAll(null)).thenReturn(clientes);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/clientes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nome").value(EXPECTED_NOME_1))
        .andExpect(jsonPath("$[1].nome").value(EXPECTED_NOME_2))
        .andExpect(jsonPath("$[0].documento").value(EXPECTED_DOCUMENTO_1))
        .andExpect(jsonPath("$[1].documento").value(EXPECTED_DOCUMENTO_2))
        .andExpect(jsonPath("$[0].rendaFamiliar").value(EXPECTED_RENDA_FAMILIAR_1))
        .andExpect(jsonPath("$[1].rendaFamiliar").value(EXPECTED_RENDA_FAMILIAR_2))
        .andExpect(jsonPath("$[0].telefone").value(EXPECTED_TELEFONE_1))
        .andExpect(jsonPath("$[1].telefone").value(EXPECTED_TELEFONE_2))
        .andExpect(jsonPath("$[0].endereco").value(EXPECTED_ENDERECO_1))
        .andExpect(jsonPath("$[1].endereco").value(EXPECTED_ENDERECO_2))
        .andExpect(jsonPath("$[0].email").value(EXPECTED_EMAIL_1))
        .andExpect(jsonPath("$[1].email").value(EXPECTED_EMAIL_2));

    verify(clienteService).findAll(null);
  }

  @Test
  public void findWithClasseSocialInvalidaTest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/clientes").param("classeSocial", "G"))
        .andExpect(status().isBadRequest());
  }
}
