package br.com.sicredi.treinamento.revendedora_plus.controller;

import br.com.sicredi.treinamento.revendedora_plus.config.ModelMapperConfig;
import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import br.com.sicredi.treinamento.revendedora_plus.rest.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
@Import(ModelMapperConfig.class)
public class ClienteControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void deveriaRetornarClientesClasseSocialDefault() throws Exception {
        when(clienteService.findAll(null)).thenReturn(new ArrayList<Cliente>());

        mvc.perform(get("/clientes")).andExpect(status().isOk());
    }

    private Cliente obterCliente() {
        Cliente c = new Cliente();
        c.setId(Long.MAX_VALUE);
        return null;
    }

}
