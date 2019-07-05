package br.com.sicredi.treinamento.revendedora_plus.repository;

import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
  List<Cliente> findByRendaFamiliarGreaterThanEqualAndRendaFamiliarLessThanEqual(
      BigDecimal minRenda, BigDecimal maxRenda);

  List<Cliente> findByRendaFamiliarGreaterThanEqual(BigDecimal minRenda);

  List<Cliente> findByRendaFamiliarLessThanEqual(BigDecimal maxRenda);
}
