package br.com.sicredi.treinamento.revendedora_plus.repository;

import br.com.sicredi.treinamento.revendedora_plus.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    List<Cliente> findAllByRendaFamiliarBetween(BigDecimal rendaMin, BigDecimal rendaMax);

    List<Cliente> findAllByRendaFamiliarGreaterThan(BigDecimal rendaMin);

}
