package br.com.sicredi.treinamento.revendedora_plus.repository;

import static org.junit.Assert.assertEquals;

import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("classpath:insert_clientes.sql")
public class ClienteRepositoryIT {

  @Autowired private ClienteRepository clienteRepository;

  @Test
  public void findByRendaFamiliarLessThanEqualTest() {
    List<Cliente> clientes =
        clienteRepository.findByRendaFamiliarLessThanEqual(BigDecimal.valueOf(1874.01));

    assertEquals(3, clientes.size());
  }

  @Test
  public void findByRendaFamiliarGreaterThanEqualTest() {
    List<Cliente> clientes =
        clienteRepository.findByRendaFamiliarGreaterThanEqual(BigDecimal.valueOf(12000.50));

    assertEquals(4, clientes.size());
  }

  @Test
  public void findByRendaFamiliarBetweenTest() {
    List<Cliente> clientes =
        clienteRepository.findByRendaFamiliarGreaterThanEqualAndRendaFamiliarLessThanEqual(
            BigDecimal.valueOf(2000.00), BigDecimal.valueOf(12000.50));

    assertEquals(7, clientes.size());
  }
}
